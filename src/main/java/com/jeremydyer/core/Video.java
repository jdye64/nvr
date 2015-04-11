package com.jeremydyer.core;

import com.makeandbuild.vessl.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by jeremydyer on 3/18/15.
 */
@Table(name = "event")
public class Video {

    @Id
    @Column(name = "video_id")
    @SaveWhen(insert = true, update = false)
    private long id;

    @Column(name = "size")
    @SaveWhen(insert = true, update = true)
    private long size;

    @Column(name = "startDate")
    @SaveWhen(insert = true, update = true)
    private Date startDate;

    @Column(name = "endDate")
    @SaveWhen(insert = true, update = true)
    private Date endDate;

    @Column(name = "fullFileName")
    @SaveWhen(insert = true, update = true)
    private String fullFileName;

    @Column(name = "idx")
    @SaveWhen(insert = true, update = true)
    private IDX idx;

    @Column(name = "motionDetectEvent")
    @SaveWhen(insert = true, update = true)
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
