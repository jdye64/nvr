package com.jeremydyer.core;

import com.jeremydyer.core.audio.AudioEncoding;
import com.jeremydyer.core.video.VideoEncoding;

import java.nio.file.Path;

/**
 * Created by jeremydyer on 3/20/15.
 */
public class IDX {

    private Path idxPath;

    //IDX File event information
    private DahuaIDXEvent idxEvent;

    //IDX File encoding information
    private DahuaIDXEncoding idxEncoding;

    public String toString() {
        return String.format("IDX[path: %s, idxEvent: %s]", idxPath, idxEvent);
    }

    public IDX() {
    }

    public Path getIdxPath() {
        return idxPath;
    }

    public void setIdxPath(Path idxPath) {
        this.idxPath = idxPath;
    }

    public DahuaIDXEvent getIdxEvent() {
        return idxEvent;
    }

    public void setIdxEvent(DahuaIDXEvent idxEvent) {
        this.idxEvent = idxEvent;
    }

    public DahuaIDXEncoding getIdxEncoding() {
        return idxEncoding;
    }

    public void setIdxEncoding(DahuaIDXEncoding idxEncoding) {
        this.idxEncoding = idxEncoding;
    }
}
