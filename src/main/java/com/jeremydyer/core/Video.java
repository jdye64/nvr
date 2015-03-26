package com.jeremydyer.core;

import java.nio.file.Path;

/**
 * Created by jeremydyer on 3/18/15.
 */
public class Video {

    private Path path;
    private IDX idx;
    private boolean motionDetectEvent = false;


    public Video(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public IDX getIdx() {
        return idx;
    }

    public void setIdx(IDX idx) {
        this.idx = idx;
    }

    public boolean isMotionDetectEvent() {
        return motionDetectEvent;
    }

    public void setMotionDetectEvent(boolean motionDetectEvent) {
        this.motionDetectEvent = motionDetectEvent;
    }

    public String toString() {
        return String.format("Video[path: %s, motionDetectEvent: %s, %s]", path, motionDetectEvent, idx);
    }
}
