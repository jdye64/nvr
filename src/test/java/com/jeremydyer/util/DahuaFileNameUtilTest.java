package com.jeremydyer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Path;

/**
 * Created by jeremydyer on 4/10/15.
 */
public class DahuaFileNameUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(DahuaFileNameUtil.class);

    @Test
    public void testIsDavFile() {
        Assert.assertTrue(DahuaFileNameUtil.isDavFile("shouldIReallyBeValid?.dav"));    //Should it though really?
        Assert.assertTrue(DahuaFileNameUtil.isDavFile("00.04.43-00.05.10[M][0@0][0].dav"));
        Assert.assertFalse(DahuaFileNameUtil.isDavFile("ExpenseReport.xslx"));
        Assert.assertFalse(DahuaFileNameUtil.isDavFile(null));
        Assert.assertFalse(DahuaFileNameUtil.isDavFile(""));
    }

    @Test
    public void testIsMotionEvent() {
        Assert.assertTrue(DahuaFileNameUtil.isMotionEvent("shouldIReallyBeValid?[M].dav")); //Should it though really?
        Assert.assertTrue(DahuaFileNameUtil.isMotionEvent("00.04.43-00.05.10[M][0@0][0].dav"));
        Assert.assertFalse(DahuaFileNameUtil.isMotionEvent("00.04.43-00.05.10[R][0@0][0].dav"));
        Assert.assertFalse(DahuaFileNameUtil.isMotionEvent("00.04.43-00.05.10[M][0@0][0]"));
        Assert.assertFalse(DahuaFileNameUtil.isMotionEvent(null));
        Assert.assertFalse(DahuaFileNameUtil.isMotionEvent(""));
    }

    @Test
    public void testIdxPathForVideoPath() {
        Path p = DahuaFileNameUtil.idxPathForVideoPath("something.dav");
        Assert.assertTrue(p.toString().endsWith(".idx"));
        Assert.assertTrue(p.toString().equals("something.idx"));
        p = DahuaFileNameUtil.idxPathForVideoPath("something");
        Assert.assertTrue(p == null);

    }
}
