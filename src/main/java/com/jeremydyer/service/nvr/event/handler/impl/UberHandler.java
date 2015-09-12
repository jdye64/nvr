package com.jeremydyer.service.nvr.event.handler.impl;

import com.jeremydyer.core.Video;
import com.jeremydyer.core.factory.VideoFactory;
import com.jeremydyer.dao.VideoDAO;
import com.jeremydyer.service.VideoConversionService;
import com.jeremydyer.service.notification.MotionEventNoficationManager;
import com.jeremydyer.service.nvr.event.handler.VideoFileEventHandler;
import com.jeremydyer.util.DahuaFileNameUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

/**
 * Really for the time being I just want things to be super simple to follow more so than the ultimate engineering
 * marvel. I'm going to place all of my current handler logic in here for now and then bust it out into the appropriate
 * handlers chained together with failure/retry logic once things become more clear to me as to what I really want
 * to accomplish. Let us explore ...
 *
 * Created by jeremydyer on 4/10/15.
 */
public class UberHandler
    implements VideoFileEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(VideoFileEventHandler.class);

    private VideoDAO videoDAO = null;
    private VideoConversionService vcs = VideoConversionService.getInstance();
    private MotionEventNoficationManager menm = MotionEventNoficationManager.getInstance();

    private String urlString = "http://10.0.1.17:8983/solr/techproducts";
    private SolrClient solr = new HttpSolrClient(urlString);

    public UberHandler(VideoDAO videoDAO) {
        this.videoDAO = videoDAO;
    }

    @Override
    public void created(Path path) {
        logger.debug(this.getClass().getName() + " created " + path.toString());
        if (path != null) {
            if (DahuaFileNameUtil.isIDXFile(path.toString())) {
                //The IDX file is created after the .dav file so we can assume the .dav file is present at this point.
                Path davPath = DahuaFileNameUtil.davPathForIDXPath(path.toString());
                Video video = VideoFactory.createVideoFromPath(davPath);
                if (video != null) {
                    video = videoDAO.save(video);
                    logger.debug("VideoDAO DB: " + videoDAO.toString());
                    if (DahuaFileNameUtil.isMotionEvent(video.getFullFileName())) {
                        logger.info("Motion event. Converting ...");
                        vcs.add(video);
                        menm.motionEventDetected(video);

//                        try {
//                            //Index the event to Solr for online viewing.
//                            SolrInputDocument document = new SolrInputDocument();
//                            document.addField("id", "552199");
//                            document.addField("name", "Gouda cheese wheel");
//                            document.addField("price", "49.99");
//                            UpdateResponse response = solr.add(document);
//
//                            solr.commit();
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
                    }
                } else {
                    logger.error("Failed to create Video object from video file path: " + path.toString());
                }
            } else {
                logger.debug(path.toString() + " is not a Dahua .dav file so I'm not interested ...");
            }
        }
    }

    @Override
    public void modified(Path path) {

    }

    @Override
    public void deleted(Path path) {

    }
}
