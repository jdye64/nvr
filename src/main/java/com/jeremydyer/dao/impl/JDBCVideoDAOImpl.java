package com.jeremydyer.dao.impl;

import com.jeremydyer.core.Camera;
import com.jeremydyer.core.Video;
import com.jeremydyer.dao.VideoDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by jeremydyer on 4/10/15.
 */
public class JDBCVideoDAOImpl
    implements VideoDAO {

    private static final Logger logger = LoggerFactory.getLogger(JDBCVideoDAOImpl.class);

    @Override
    public Video save(Video video) {
        return null;
    }

    @Override
    public Video get(long videoId) {
        return null;
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
}
