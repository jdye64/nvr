package com.jeremydyer.util;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by jeremydyer on 3/20/15.
 */
public class DahuaFileNameUtil {

    /**
     *
     * @param videoPath
     * @return
     */
    public static boolean isMotionEvent(Path videoPath) {
        if (videoPath != null) {
            if (videoPath.toString().contains("[M]")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static Path idxPathForVideoPath(Path videoPath) {
        if (videoPath != null) {
            Path idx = Paths.get(FilenameUtils.removeExtension(videoPath.toString()) + ".idx");
            if (Files.exists(idx)) {
                return idx;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
