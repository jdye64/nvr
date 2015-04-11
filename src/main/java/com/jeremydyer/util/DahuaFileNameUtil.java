package com.jeremydyer.util;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by jeremydyer on 3/20/15.
 */
public class DahuaFileNameUtil {

    public static boolean isIDXFile(String fullFileName) {
        if (fullFileName != null) {
            if (fullFileName.endsWith(".idx")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Determines if the Path is a Dahua IP camera .dav file.
     *
     * @param fullFileName
     *  Path to the file in question.
     *
     * @return
     *  True if .dav and false otherwise.
     */
    public static boolean isDavFile(String fullFileName) {
        if (fullFileName != null) {
            if (fullFileName.endsWith(".dav")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isMP4File(String fullFileName) {
        if (fullFileName != null) {
            if (fullFileName.endsWith(".mp4")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isVideoFile(String fullFileName) {
        if (isDavFile(fullFileName) || isMP4File(fullFileName)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks to make sure that a video is a dav file and motion event. Take not the file must also be a .dav file
     * for this to return true!
     *
     * @param fullFileName
     * @return
     */
    public static boolean isMotionEvent(String fullFileName) {
        if (fullFileName != null) {
            if (fullFileName.contains("[M]") && isDavFile(fullFileName)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static Path idxPathForVideoPath(String fullFileName) {
        if (fullFileName != null && isDavFile(fullFileName)) {
            Path idx = Paths.get(FilenameUtils.removeExtension(fullFileName) + ".idx");
            return idx;
        } else {
            return null;
        }
    }

    public static Path davPathForIDXPath(String idxFullName) {
        if (idxFullName != null && isIDXFile(idxFullName)) {
            Path davPath = Paths.get(FilenameUtils.removeExtension(idxFullName) + ".dav");
            return davPath;
        } else {
            return null;
        }
    }
}
