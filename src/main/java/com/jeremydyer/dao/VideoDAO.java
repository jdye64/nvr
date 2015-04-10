package com.jeremydyer.dao;

import com.jeremydyer.core.Camera;
import com.jeremydyer.core.Video;

import java.util.Date;
import java.util.List;

/**
 * Created by jeremydyer on 4/8/15.
 */
public interface VideoDAO {

    /**
     * Saves a new video to the video database.
     *
     * @param video
     *
     * @return
     *  New video object that was saved in the database
     */
    public Video save(Video video);

    /**
     * Gets a specific video event by its ID
     * @param videoId
     * @return
     */
    public Video get(long videoId);

    /**
     * Gets all of the videos between a particular date range.
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Video> getVideosByDateRange(Date startDate, Date endDate);

    /**
     * Get all of the events for a particular camera
     * @param camera
     * @return
     */
    public List<Video> getVideosForCamera(Camera camera);

    /**
     * Gets the last N motion events that have occurred across all available cameras
     *
     * @param n
     *  Number of past events to get
     *
     * @return
     *  List of Video objects
     */
    public List<Video> getLastNMotionVideos(int n);
}
