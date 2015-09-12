package com.jeremydyer.service;

import com.jeremydyer.NVRConfiguration;
import com.jeremydyer.util.DahuaFileNameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * Upon creation, deletion, or modification of monitored files this service processes
 * them accordingly.
 *
 * Created by jeremydyer on 3/14/15.
 */
public class NVRVideoFileEventService
    implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(NVRVideoFileEventService.class);

    private VideoConversionService videoConversionService = VideoConversionService.getInstance();
    private NVRConfiguration nvrConfiguration = null;

    public NVRVideoFileEventService(NVRConfiguration nvrConfiguration) {
       this.nvrConfiguration = nvrConfiguration;
    }

    @Override
    public void run() {
        logger.debug("Starting " + this.getClass().getName() + " ...");

        //Creates a new DirectoryWatcher instance to monitor the NVR base directory.
        try {
            DirectoryWatcher nvrBaseDirWatcher = new DirectoryWatcher(Paths.get(this.nvrConfiguration.getNvrVideoDir()), true, this);
            nvrBaseDirWatcher.run();
            for(;;) {
                Thread.sleep(300000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void handleEvent(WatchEvent<?> event, Path dir) {
        // Context for directory entry event is the file name of entry
        WatchEvent<Path> ev = DirectoryWatcher.cast(event);
        Path name = ev.context();
        Path child = dir.resolve(name);

        logger.trace(child.toString() + " Event: " + event.kind().toString());

        if (event.kind() == ENTRY_CREATE) {
            logger.debug(this.getClass().getName() + " created " + child.toString());
            if (child != null) {
                if (DahuaFileNameUtil.isIDXFile(child.toString())) {
                    //The IDX file is created after the .dav file so we can assume the .dav file is present at this point.
                    Path davPath = DahuaFileNameUtil.davPathForIDXPath(child.toString());
                    if (davPath != null) {
                        if (DahuaFileNameUtil.isMotionEvent(davPath.toString())) {
                            logger.info("Motion event. Converting ...");
                            videoConversionService.add(davPath);
                        }
                    } else {
                        logger.error("Failed to create Video object from video file path: " + davPath.toString());
                    }
                } else {
                    logger.debug(child.toString() + " is not a Dahua .dav file so I'm not interested ...");
                }
            }
        } else if (event.kind() == ENTRY_MODIFY) {
            //logger.info("Modified: " + child.toString());
        } else if (event.kind() == ENTRY_DELETE) {
            logger.info("Deleted: " + child.toString());
        } else {
            logger.warn("Encountered unknown java.nio file event: " + event.kind());
        }
    }
}
