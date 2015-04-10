package com.jeremydyer.core.factory;

import com.jeremydyer.core.Video;
import com.jeremydyer.util.DahuaFileNameUtil;

import java.io.File;
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

        ///media/nvr/BackYard/2015-04-11/001/dav/03/03.28.59-03.29.26[M][0@0][0].dav
        File file = new File(fullFileName);
        if (file != null && file.exists()) {
            v.setSize(file.length());
        } else {
            v.setSize(-1l);
        }

        //Locate and parse the IDX file for the video file
        v.setIdx(IDXFactory.createIDXFromPath(DahuaFileNameUtil.idxPathForVideoPath(v.getFullFileName())));

        return v;
    }
}
