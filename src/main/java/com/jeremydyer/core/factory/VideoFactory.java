package com.jeremydyer.core.factory;

import com.jeremydyer.core.Video;
import com.jeremydyer.util.DahuaFileNameUtil;

import java.nio.file.Path;

/**
 * Created by jeremydyer on 3/20/15.
 */
public class VideoFactory {

    public static Video createVideoFromPath(Path videoPath) {
        if (videoPath != null) {
            return VideoFactory.createVideoFromFullFileName(videoPath.toString());
        }
        return null;
    }

    public static Video createVideoFromFullFileName(String fullFileName) {
        Video v = new Video(fullFileName);
        v.setMotionDetectEvent(DahuaFileNameUtil.isMotionEvent(v.getFullFileName()));

        //Locate and parse the IDX file for the video file
        v.setIdx(IDXFactory.createIDXFromPath(DahuaFileNameUtil.idxPathForVideoPath(v.getFullFileName())));

        return v;
    }
}
