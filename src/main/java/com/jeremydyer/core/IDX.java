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
    private boolean audioEnabled;
    private AudioEncoding audioEncoding;
    private boolean videoEnabled;
    private VideoEncoding videoEncoding;

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
