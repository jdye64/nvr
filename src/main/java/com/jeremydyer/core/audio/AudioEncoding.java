package com.jeremydyer.core.audio;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jeremydyer on 3/18/15.
 */
public class AudioEncoding {

    @JsonProperty("Bitrate")
    private int bitrate;

    @JsonProperty("Compression")
    private String compression;

    @JsonProperty("Depth")
    private int depth;

    @JsonProperty("Frequency")
    private int frequency;

    @JsonProperty("Mode")
    private int mode;

    @JsonProperty("Pack")
    private String pack;

    //Default constructor to satisfy Jackson ...
    public AudioEncoding() {}

    public AudioEncoding(int bitrate, String compression, int depth, int frequency, int mode, String pack) {
        this.bitrate = bitrate;
        this.compression = compression;
        this.depth = depth;
        this.frequency = frequency;
        this.mode = mode;
        this.pack = pack;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public String getCompression() {
        return compression;
    }

    public void setCompression(String compression) {
        this.compression = compression;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }
}
