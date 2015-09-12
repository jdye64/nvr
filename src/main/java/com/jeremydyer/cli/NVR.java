package com.jeremydyer.cli;

import com.jeremydyer.NVRConfiguration;
import com.jeremydyer.service.NVRVideoFileEventService;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Master CLI for launching the all encompassing Network Video Recorder application
 *
 * Created by jeremydyer on 3/22/15.
 */
public class NVR
        extends ConfiguredCommand<NVRConfiguration> {

    final static Logger logger = LoggerFactory.getLogger(NVR.class);

    public NVR(String name, String description) {
        super(name, description);
    }

    @Override
    protected void run(Bootstrap<NVRConfiguration> nvrConfigurationBootstrap, Namespace namespace, NVRConfiguration nvrConfiguration) throws Exception {

        //*** Start all continuously running threads ***

        //Uses java.nio to watch the NVR base directory for file events
        NVRVideoFileEventService fes = new NVRVideoFileEventService(nvrConfiguration);
        fes.run();

        //Enter an infinite loop to keep the program alive
        for(;;) {
            Thread.sleep(300000);
        }
    }
}
