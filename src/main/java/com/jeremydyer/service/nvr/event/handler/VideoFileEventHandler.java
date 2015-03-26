package com.jeremydyer.service.nvr.event.handler;

import java.nio.file.Path;

/**
 * NVRFileEventService will invoke a chain of FileEventHandler instance for each event encountered
 *
 * Created by jeremydyer on 3/26/15.
 */
public interface VideoFileEventHandler {

    public void created(Path path);
    public void modified(Path path);
    public void deleted(Path path);
}
