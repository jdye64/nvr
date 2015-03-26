package com.jeremydyer.service.nvr.event.handler.impl;

import com.jeremydyer.service.nvr.event.handler.VideoFileEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

/**
 * Handles events around video motion event files that are created on disk
 *
 * Created by jeremydyer on 3/26/15.
 */
public class MotionVideoFileEventHandler
    implements VideoFileEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(MotionVideoFileEventHandler.class);

    @Override
    public void created(Path path) {
        logger.info(this.getClass().getName() + " created " + path.toString());
    }

    @Override
    public void modified(Path path) {
        //Not interested ...
    }

    @Override
    public void deleted(Path path) {
        logger.info(this.getClass().getName() + " deleted " + path.toString());
    }
}
