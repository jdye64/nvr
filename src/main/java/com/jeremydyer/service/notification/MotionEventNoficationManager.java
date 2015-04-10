package com.jeremydyer.service.notification;

import com.jeremydyer.core.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jeremydyer on 4/10/15.
 */
public class MotionEventNoficationManager {

    private static final Logger logger = LoggerFactory.getLogger(MotionEventNoficationManager.class);
    private static MotionEventNoficationManager instance = null;

    protected MotionEventNoficationManager() {
        // Exists only to defeat instantiation.
    }
    public static MotionEventNoficationManager getInstance() {
        if(instance == null) {
            instance = new MotionEventNoficationManager();
        }
        return instance;
    }

    /**
     * Invoked when a motion video file is created. Expands into a rules engine that determines if a notification
     * to the home owners should be sent out or ignored based on user defined rules.
     *
     * @param motionVideo
     *  Video file created for the motion event
     */
    public void motionEventDetected(Video motionVideo) {
        logger.debug("MotionEvent: " + motionVideo.toString());
    }
}
