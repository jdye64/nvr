package com.jeremydyer.cli;

import com.jeremydyer.NVRConfiguration;
import com.jeremydyer.service.nvr.event.NVRVideoFileEventService;
import com.jeremydyer.service.storage.DailyStorageReportRunnable;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import it.sauronsoftware.cron4j.Scheduler;
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
        // Creates a Scheduler instance.
        Scheduler s = new Scheduler();

        //*** Start all continuously running threads ***

        //Uses java.nio to watch the NVR base directory for file events
        NVRVideoFileEventService fes = new NVRVideoFileEventService(nvrConfiguration);
        fes.run();

        //*** End continuously running threads ***

        // Schedule all of the NVR tasks
        //s.schedule("* * * * *", new StorageMetricsRunnable(nvrConfiguration));  //Every minute
        s.schedule("0 8 * * *", new DailyStorageReportRunnable(nvrConfiguration));

        // Starts the scheduler.
        s.start();

        //Enter an infinite loop to keep the program alive
        for(;;) {
            Thread.sleep(300000);
        }
    }
}
