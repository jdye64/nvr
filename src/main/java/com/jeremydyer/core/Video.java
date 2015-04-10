package com.jeremydyer.core;

import java.util.Date;

/**
 * Created by jeremydyer on 3/18/15.
 */
public class Video {

    private long id;
    private long size;
    private Date startDate;
    private Date endDate;
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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String toString() {
        return String.format("Video[fullFileName: %s, motionDetectEvent: %s, fileSize: %s, startDate: %s, endDate: %s, %s]", fullFileName, motionDetectEvent, size, startDate, endDate, idx);
    }
}
