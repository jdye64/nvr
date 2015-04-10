package com.jeremydyer.service;

import com.jeremydyer.core.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Service for converting .dav files to .mp4 files.
 *
 * Created by jeremydyer on 3/18/15.
 */
public class VideoConversionService {

    private static final Logger logger = LoggerFactory.getLogger(VideoConversionService.class);
    private static VideoConversionService instance = null;

    protected VideoConversionService() {
        // Exists only to defeat instantiation.
    }
    public static VideoConversionService getInstance() {
        if(instance == null) {
            instance = new VideoConversionService();
        }
        return instance;
    }

    private ConcurrentLinkedDeque<Video> queue = new ConcurrentLinkedDeque<Video>();

    public void add(Video davFile) {
        queue.add(davFile);
        runThread();
    }

    private int runningThreads = 0;
    private int maxRunningThreads = 3;

    private void runThread() {
        if (runningThreads < maxRunningThreads) {
            Video f = queue.poll();
            if (f != null) {
                new Thread(new FFMPEGThread(f)).start();
            }
        } else {
            System.out.println("Queue is already saturated. Waiting for available threads to free up");
        }
    }

    private synchronized void incrementRunningThreads() {
        runningThreads++;
        System.out.println("Incremented Running threads -> " + runningThreads);
    }

    private synchronized void decrementRunningThreads() {
        runningThreads--;
        System.out.println("Decremented Running threads -> " + runningThreads);
        runThread();
    }

    private class FFMPEGThread implements Runnable {

        private Video dav;

        public FFMPEGThread(Video dav) {
            incrementRunningThreads();
            this.dav = dav;
        }

        @Override
        public void run() {

            //Create the File object from the String fullName
            File origFile = new File(dav.getFullFileName());
            if (origFile != null && origFile.exists()) {
                //Builds the Process that will be used to convert the .dav file to .mp4
                String outputFile = origFile.getParent() + File.separator + origFile.getName().replace(".dav", ".mp4");
                ProcessBuilder pb = new ProcessBuilder("ffmpeg", "-y", "-i", dav.getFullFileName(), "-vcodec", "libx264", "-crf", "24", outputFile);
                pb.directory(origFile.getParentFile());

                System.out.println("Running FFMPEG thread for Video: " + dav.getFullFileName() + " -> " + outputFile);
                try {
                    Process p = pb.start();
                    p.waitFor();

                    //Now delete the original .dav file since it is no longer needed
                    if (origFile.delete()) {
                        System.out.println("Successfully deleted: " + dav.getFullFileName());
                    } else {
                        System.out.println("Failed to delete: " + dav.getFullFileName());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                logger.warn("Dav original file could not be found: " + dav.getFullFileName());
            }

            //After processing is complete ...
            decrementRunningThreads();
        }
    }
}
