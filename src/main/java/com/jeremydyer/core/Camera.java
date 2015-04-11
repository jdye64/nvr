package com.jeremydyer.core;

import java.net.InetAddress;

/**
 * Created by jeremydyer on 4/8/15.
 */
public class Camera {

    private long cameraId;
    private String cameraName;
    private InetAddress cameraIP;

    public Camera() {}

    public Camera(long cameraId) {
        this.cameraId = cameraId;
    }

    public long getCameraId() {
        return cameraId;
    }

    public void setCameraId(long cameraId) {
        this.cameraId = cameraId;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public InetAddress getCameraIP() {
        return cameraIP;
    }

    public void setCameraIP(InetAddress cameraIP) {
        this.cameraIP = cameraIP;
    }
}
