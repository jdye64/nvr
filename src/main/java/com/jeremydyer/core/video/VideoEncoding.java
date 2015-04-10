package com.jeremydyer.core.video;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jeremydyer on 3/18/15.
 */
public class VideoEncoding {

    @JsonProperty("BitRate")
    private int bitrate;

    @JsonProperty("BitRateControl")
    private String bitrateControl;  //EX: VBR or CBR

    @JsonProperty("Compression")
    private String compression;     //EX: H.264

    @JsonProperty("CustomResolutionName")
    private String customResolutionName;

    @JsonProperty("FPS")
    private int fps;

    @JsonProperty("GOP")
    private int gop;

    @JsonProperty("Height")
    private int height;

    @JsonProperty("Pack")
    private String pack;

    @JsonProperty("Profile")
    private String profile;

    @JsonProperty("Quality")
    private int quality;

    @JsonProperty("QualityRange")
    private int qualityRange;

    @JsonProperty("Width")
    private int width;

    //Default constructor to satisfy Jackson ...
    public VideoEncoding() {}

    public VideoEncoding(int bitrate, String bitrateControl, String compression, String customResolutionName, int fps, int gop, int height, String pack, String profile, int quality, int qualityRange, int width) {
        this.bitrate = bitrate;
        this.bitrateControl = bitrateControl;
        this.compression = compression;
        this.customResolutionName = customResolutionName;
        this.fps = fps;
        this.gop = gop;
        this.height = height;
        this.pack = pack;
        this.profile = profile;
        this.quality = quality;
        this.qualityRange = qualityRange;
        this.width = width;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public String getBitrateControl() {
        return bitrateControl;
    }

    public void setBitrateControl(String bitrateControl) {
        this.bitrateControl = bitrateControl;
    }

    public String getCompression() {
        return compression;
    }

    public void setCompression(String compression) {
        this.compression = compression;
    }

    public String getCustomResolutionName() {
        return customResolutionName;
    }

    public void setCustomResolutionName(String customResolutionName) {
        this.customResolutionName = customResolutionName;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public int getGop() {
        return gop;
    }

    public void setGop(int gop) {
        this.gop = gop;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getQualityRange() {
        return qualityRange;
    }

    public void setQualityRange(int qualityRange) {
        this.qualityRange = qualityRange;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
