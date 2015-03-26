package com.jeremydyer.service.nvr.index;

import com.jeremydyer.NVRConfiguration;
import com.jeremydyer.core.Video;
import com.jeremydyer.core.factory.VideoFactory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Maintains the index/database for the video files managed by the NVR application.
 *
 * Created by jeremydyer on 3/19/15.
 */
public class VideoIndexManager {

    private NVRConfiguration nvrConfiguration;

    public VideoIndexManager(NVRConfiguration nvrConfiguration) {
        this.nvrConfiguration = nvrConfiguration;
    }

    public void indexVideoFiles(List<Path> videoFiles) {
        System.out.println("Indexing video files: " + videoFiles.size());

        ArrayList<Video> videos = new ArrayList<>();
        if (videoFiles != null) {
            for (Path vf : videoFiles) {
                System.out.println("\t" + vf.toString());
                videos.add(VideoFactory.createVideoFromPath(vf));
            }
        }

        System.out.println("Created " + videos.size() + " video files to index");
    }
}
