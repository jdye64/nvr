package com.jeremydyer.service.nvr.index;

import com.jeremydyer.NVRConfiguration;
import com.jeremydyer.core.Video;
import com.jeremydyer.core.factory.VideoFactory;
import com.jeremydyer.dao.VideoDAO;
import com.jeremydyer.util.DahuaFileNameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by jeremydyer on 4/10/15.
 */
public class ApplicationStartupIndexingRunnable
    implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupIndexingRunnable.class);

    private NVRConfiguration configuration = null;
    private VideoDAO videoDAO = null;

    public ApplicationStartupIndexingRunnable(NVRConfiguration configuration, VideoDAO videoDAO) {
        this.configuration = configuration;
        this.videoDAO = videoDAO;
    }

    @Override
    public void run() {
        logger.debug(this.getClass().getName() + " Building initial index ...");

        try {
            Files.walkFileTree(Paths.get(this.configuration.getNvrVideoDir()), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                        throws IOException {

                    if (DahuaFileNameUtil.isVideoFile(dir.toString())) {
                        Video video = VideoFactory.createVideoFromPath(dir);
                        if (video != null) {
                            video = videoDAO.save(video);
                            logger.debug("VideoDAO DB: " + videoDAO.toString());
                        }
                    }

                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
