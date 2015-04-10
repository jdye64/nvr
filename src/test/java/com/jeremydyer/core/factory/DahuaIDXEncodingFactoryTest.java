package com.jeremydyer.core.factory;

import com.jeremydyer.core.DahuaIDXEncoding;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by jeremydyer on 4/10/15.
 */
public class DahuaIDXEncodingFactoryTest {

    @Test
    public void testCreateDahuaIDXEncodingFromJSON() {
        DahuaIDXEncoding enc = DahuaIDXEncodingFactory.createDahuaIDXEncodingFromJSON("EncodeFormat={ \"Audio\" : { \"Bitrate\" : 64, \"Compression\" : \"G.711A\", \"Depth\" : 16, \"Frequency\" : 8000, \"Mode\" : 0, \"Pack\" : \"DHAV\" }, \"AudioEnable\" : true, \"Video\" : { \"BitRate\" : 4096, \"BitRateControl\" : \"CBR\", \"Compression\" : \"H.264\", \"CustomResolutionName\" : \"1080P\", \"FPS\" : 20, \"GOP\" : 60, \"Height\" : 1536, \"Pack\" : \"DHAV\", \"Profile\" : \"High\", \"Quality\" : 4, \"QualityRange\" : 6, \"Width\" : 2048 }, \"VideoEnable\" : true }");
        Assert.assertNotNull(enc);
        Assert.assertTrue(enc.isAudioEnabled() == true);
        Assert.assertTrue(enc.isVideoEnabled() == true);
        Assert.assertTrue(enc.getAudioEncoding() != null);
        Assert.assertTrue(enc.getVideoEncoding() != null);
    }
}
