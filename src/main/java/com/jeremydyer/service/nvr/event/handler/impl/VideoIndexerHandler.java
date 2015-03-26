package com.jeremydyer.service.nvr.event.handler.impl;

import com.jeremydyer.service.nvr.event.handler.VideoFileEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

/**
 * When files are written to the NVR they should be index in order to keep track of the video files that are
 * present on the NVR. This code takes care of just that.
 *
 * Created by jeremydyer on 3/26/15.
 */
public class VideoIndexerHandler
        implements VideoFileEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(VideoIndexerHandler.class);

    @Override
    public void created(Path path) {
        logger.info(this.getClass().getName() + " created " + path.toString());
    }

    @Override
    public void modified(Path path) {
        logger.info(this.getClass().getName() + " modified " + path.toString());
    }

    @Override
    public void deleted(Path path) {
        logger.info(this.getClass().getName() + " deleted " + path.toString());
    }
}
