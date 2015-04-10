package com.jeremydyer.dao.impl;

import com.jeremydyer.core.Camera;
import com.jeremydyer.core.Video;
import com.jeremydyer.dao.VideoDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jeremydyer on 4/8/15.
 */
public class InMemoryVideoDAOImpl
    implements VideoDAO {

    private static final Logger logger = LoggerFactory.getLogger(VideoDAO.class);

    private ArrayList<Video> db = new ArrayList<>();
    private long uid = 0l;

    private synchronized long generatedNewID() {
        return uid++;
    }

    @Override
    public Video save(Video video) {
        logger.debug("Saving video: " + video.toString());
        video.setId(generatedNewID());
        db.add(video);
        return video;
    }

    @Override
    public Video get(long videoId) {
        logger.debug("Getting Video with ID: " + videoId);
        Video video = null;
        for (Video v : db) {
            if (v != null && v.getId() == videoId) {
                video = v;
                break;
            }
        }
        return video;
    }

    @Override
    public List<Video> getVideosByDateRange(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<Video> getVideosForCamera(Camera camera) {
        return null;
    }

    @Override
    public List<Video> getLastNMotionVideos(int n) {
        return null;
    }

    public String toString() {
        return String.format("InMemoryVideoDAOImpl[Size: %s, UUID: %s]", db.size(),uid);
    }
}
