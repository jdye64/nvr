package com.jeremydyer.service.nvr.event;

import com.jeremydyer.NVRConfiguration;
import com.jeremydyer.core.Video;
import com.jeremydyer.dao.VideoDAO;
import com.jeremydyer.service.DirectoryWatcher;
import com.jeremydyer.service.VideoConversionService;
import com.jeremydyer.service.nvr.event.handler.VideoFileEventHandler;
import com.jeremydyer.service.nvr.event.handler.impl.MotionVideoFileEventHandler;
import com.jeremydyer.service.nvr.event.handler.impl.UberHandler;
import com.jeremydyer.service.nvr.event.handler.impl.VideoIndexerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.List;

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
    private VideoDAO videoDAO = null;

    private List<VideoFileEventHandler> handlers = null;

    public NVRVideoFileEventService(NVRConfiguration nvrConfiguration, VideoDAO videoDAO) {

        this.videoDAO = videoDAO;

       //TODO: This smells bad. Prefer some sort of dependency injection later.
       handlers = new ArrayList<>();
       //handlers.add(new MotionVideoFileEventHandler());
       //handlers.add(new VideoIndexerHandler());
        handlers.add(new UberHandler(this.videoDAO));

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

        if (event.kind() == ENTRY_CREATE) {
            for (VideoFileEventHandler hand : this.handlers) {
                hand.created(child);
            }
        } else if (event.kind() == ENTRY_MODIFY) {
            for (VideoFileEventHandler hand : this.handlers) {
                hand.modified(child);
            }
        } else if (event.kind() == ENTRY_DELETE) {
            for (VideoFileEventHandler hand : this.handlers) {
                hand.deleted(child);
            }
        } else {
            logger.warn("Encountered unknown java.nio file event: " + event.kind());
        }
    }
}
