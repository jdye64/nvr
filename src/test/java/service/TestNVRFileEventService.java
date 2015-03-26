package service;

import com.jeremydyer.service.NVRFileEventService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by jeremydyer on 3/14/15.
 */
public class TestNVRFileEventService {

    NVRFileEventService nfes = null;

    @BeforeClass
    public void setup() {
        nfes = new NVRFileEventService();
    }

    @Test
    public void testSomething() {
        System.out.println("Testing something!");
        //MotionEvent me = nfes.parseMotionEventFromFile("/home/jeremy/home/jeremy/dahua/Basement4300S/2015-03-14/001/dav/09/09.18.16-09.20.40[M][0@0][0].dav");
    }
}
