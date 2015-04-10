package com.jeremydyer.core;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by jeremydyer on 4/10/15.
 */
public class VideoTest {

    @Test
    public void testVideoCreation() {
        String fileName = "sampleFileName.dav";
        Video v = new Video(fileName);
        Assert.assertNotNull(v);
        Assert.assertTrue(v.getFullFileName().equals(fileName));
    }
}
