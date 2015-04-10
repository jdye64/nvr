package com.jeremydyer.core.factory;

import com.jeremydyer.core.DahuaIDXEvent;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by jeremydyer on 4/10/15.
 */
public class DahuaIDXEventFactoryTest {

    @Test
    public void testCreateDahuaIDXEvent() {
        DahuaIDXEvent idxEvent = DahuaIDXEventFactory.createDahuaIDXEventFromJSON("Event={ \"Action\" : \"Start\", \"Index\" : 0, \"Name\" : \"VideoMotion\" }");
        Assert.assertNotNull(idxEvent);
        Assert.assertTrue(idxEvent.getAction().equals("Start"));
        Assert.assertTrue(idxEvent.getIndex() == 0);
        Assert.assertTrue(idxEvent.getName().equals("VideoMotion"));
    }
}
