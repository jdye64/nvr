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
    private String eventAction;
    private int eventIndex;
    private String eventName;

    //IDX File encoding information
    private boolean audioEnabled;
    private AudioEncoding audioEncoding;
    private boolean videoEnabled;
    private VideoEncoding videoEncoding;

    public String toString() {
        return String.format("IDX[path: %s, eventAction: %s, eventIndex: %s, eventName: %s]", idxPath, eventAction, eventIndex, eventName);
    }

    public IDX() {
    }

    public Path getIdxPath() {
        return idxPath;
    }

    public void setIdxPath(Path idxPath) {
        this.idxPath = idxPath;
    }

    public String getEventAction() {
        return eventAction;
    }

    public void setEventAction(String eventAction) {
        this.eventAction = eventAction;
    }

    public int getEventIndex() {
        return eventIndex;
    }

    public void setEventIndex(int eventIndex) {
        this.eventIndex = eventIndex;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public boolean isAudioEnabled() {
        return audioEnabled;
    }

    public void setAudioEnabled(boolean audioEnabled) {
        this.audioEnabled = audioEnabled;
    }

    public AudioEncoding getAudioEncoding() {
        return audioEncoding;
    }

    public void setAudioEncoding(AudioEncoding audioEncoding) {
        this.audioEncoding = audioEncoding;
    }

    public boolean isVideoEnabled() {
        return videoEnabled;
    }

    public void setVideoEnabled(boolean videoEnabled) {
        this.videoEnabled = videoEnabled;
    }

    public VideoEncoding getVideoEncoding() {
        return videoEncoding;
    }

    public void setVideoEncoding(VideoEncoding videoEncoding) {
        this.videoEncoding = videoEncoding;
    }
}
