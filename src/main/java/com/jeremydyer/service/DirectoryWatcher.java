package com.jeremydyer.service;

import com.jeremydyer.service.nvr.event.NVRVideoFileEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.LinkOption.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;

/**
 * Example to watch a directory (or tree) for changes to files.
 *
 * Created by jeremydyer on 3/14/15.
 */
public class DirectoryWatcher
    implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(DirectoryWatcher.class);

    private final WatchService watcher;
    private final Map<WatchKey,Path> keys;
    private final boolean recursive;
    private boolean trace = false;
    private NVRVideoFileEventService fileEventService = null;

    private ArrayList<String> dirsToIgnore = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }

    private boolean ignoreDirectory(Path dir) {
        if (dir != null && dir.toFile().isDirectory()) {
            String d = dir.getFileName().toString();
            logger.info("Checking if directory: " + d + " should be ignored");
            for (String dirName : dirsToIgnore) {
                if (dirName.equals(d)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Register the given directory with the WatchService
     */
    private void register(Path dir) throws IOException {
        if (dir != null && !ignoreDirectory(dir)) {
            WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
            if (trace) {
                Path prev = keys.get(key);
                if (prev == null) {
                    logger.debug("register: %s\n", dir);
                } else {
                    if (!dir.equals(prev)) {
                        logger.debug("update: %s -> %s\n", prev, dir);
                    }
                }
            }
            keys.put(key, dir);
        } else {
            logger.info("Ignoring directory: " + dir.toString());
        }
    }

    /**
     * Register the given directory, and all its sub-directories, with the
     * WatchService.
     */
    private void registerAll(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException
            {
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Creates a WatchService and registers the given directory
     */
    public DirectoryWatcher(Path dir, boolean recursive, NVRVideoFileEventService fileEventService) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey,Path>();
        this.recursive = recursive;
        this.fileEventService = fileEventService;

        if (recursive) {
            logger.debug("Scanning %s ...\n", dir);
            registerAll(dir);
            logger.debug("Done.");
        } else {
            register(dir);
        }

        // enable trace after initial registration
        this.trace = true;
    }

    @Override
    public void run() {
        for (;;) {

            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                logger.error("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                fileEventService.handleEvent(event, dir);

                // TBD - provide example of how OVERFLOW event is handled
                if (kind == OVERFLOW) {
                    continue;
                }

                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);

                // if directory is created, and watching recursively, then
                // register it and its sub-directories
                if (recursive && (kind == ENTRY_CREATE)) {
                    try {
                        if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                            registerAll(child);
                        }
                    } catch (IOException x) {
                        // ignore to keep sample readbale
                    }
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }

}