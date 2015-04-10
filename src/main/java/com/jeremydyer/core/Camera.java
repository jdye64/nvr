package com.jeremydyer.core;

/**
 * Created by jeremydyer on 4/8/15.
 */
public class Camera {

    private long cameraId;

    public Camera(long cameraId) {
        this.cameraId = cameraId;
    }

    public long getCameraId() {
        return cameraId;
    }

    public void setCameraId(long cameraId) {
        this.cameraId = cameraId;
    }
}
