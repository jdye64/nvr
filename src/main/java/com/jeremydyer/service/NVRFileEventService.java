package com.jeremydyer.service;

import com.jeremydyer.core.Video;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

/**
 * Upon creation, deletion, or modification of monitored files this service processes
 * them accordingly.
 *
 * Created by jeremydyer on 3/14/15.
 */
public class NVRFileEventService {

    private VideoConversionService videoConversionService = VideoConversionService.getInstance();

    public NVRFileEventService() {
        System.out.println("Creating NVRFileEventService");
    }

    public void handleEvent(WatchEvent<?> event, Path dir) {
        // Context for directory entry event is the file name of entry
        WatchEvent<Path> ev = DirectoryWatcher.cast(event);
        Path name = ev.context();
        Path child = dir.resolve(name);

        if (event.kind() == ENTRY_CREATE) {
            if (isMotionDetectionFile(name)) {
                videoConversionService.add(new Video(child));
            }
        }
    }

    public boolean isMotionDetectionFile(Path createdFile) {
        if (createdFile != null) {
            String name = createdFile.toString();

            //This is a temporary file and we need to ignore it until the tailing "_" is removed
            if (name.endsWith("_")) {
                return false;
            }

            if (name.endsWith(".dav") && name.contains("[M]")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
