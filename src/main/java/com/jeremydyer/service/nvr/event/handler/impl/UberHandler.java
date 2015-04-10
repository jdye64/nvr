package com.jeremydyer.service.nvr.event.handler.impl;

import com.jeremydyer.core.Video;
import com.jeremydyer.core.factory.VideoFactory;
import com.jeremydyer.dao.VideoDAO;
import com.jeremydyer.service.VideoConversionService;
import com.jeremydyer.service.notification.MotionEventNoficationManager;
import com.jeremydyer.service.nvr.event.handler.VideoFileEventHandler;
import com.jeremydyer.util.DahuaFileNameUtil;
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
                        vcs.add(video);
                        menm.motionEventDetected(video);
                    } else {
                        //Not a motion event for now lets just do nothing ...
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
