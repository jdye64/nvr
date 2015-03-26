package com.jeremydyer.core.factory;

import com.jeremydyer.core.Video;
import com.jeremydyer.util.DahuaFileNameUtil;

import java.nio.file.Path;

/**
 * Created by jeremydyer on 3/20/15.
 */
public class VideoFactory {

    public static Video createVideoFromPath(Path videoPath) {
        Video v = new Video(videoPath);
        v.setMotionDetectEvent(DahuaFileNameUtil.isMotionEvent(v.getPath()));

        //Locate and parse the IDX file for the video file
        v.setIdx(IDXFactory.createIDXFromPath(DahuaFileNameUtil.idxPathForVideoPath(v.getPath())));

        return v;
    }
}
