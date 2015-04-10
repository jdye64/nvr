package com.jeremydyer.core;

/**
 * Created by jeremydyer on 3/18/15.
 */
public class Video {

    private long id;
    private String fullFileName;
    private IDX idx;
    private boolean motionDetectEvent = false;

    public Video(String fullFileName) {
        this.fullFileName = fullFileName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullFileName() {
        return fullFileName;
    }

    public void setFullFileName(String fullFileName) {
        this.fullFileName = fullFileName;
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
        return String.format("Video[fullFileName: %s, motionDetectEvent: %s, %s]", fullFileName, motionDetectEvent, idx);
    }
}
