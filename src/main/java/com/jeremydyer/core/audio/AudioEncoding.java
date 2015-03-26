package com.jeremydyer.core.audio;

/**
 * Created by jeremydyer on 3/18/15.
 */
public class AudioEncoding {

    private int bitrate;
    private String compression;
    private int depth;
    private int frequency;
    private int mode;
    private String pack;

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
