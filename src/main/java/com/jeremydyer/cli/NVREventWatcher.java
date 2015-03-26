package com.jeremydyer.cli;

import com.jeremydyer.NVRConfiguration;
import com.jeremydyer.service.DirectoryWatcher;
import com.jeremydyer.service.NVRFileEventService;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import java.nio.file.*;

/**
 * Watches a standard linux filesystem directory for newly created files. The monitoring process is ran
 * on an independent thread and
 *
 * Created by jeremydyer on 3/13/15.
 */
public class NVREventWatcher extends ConfiguredCommand<NVRConfiguration> {

    final static Logger logger = LoggerFactory.getLogger(NVREventWatcher.class);

    public NVREventWatcher(String name, String description) {
        super(name, description);
    }

    @Override
    protected void run(Bootstrap<NVRConfiguration> dyerConfigurationBootstrap, Namespace namespace, NVRConfiguration dyerConfiguration) throws Exception {
        Path pathToWatch = FileSystems.getDefault().getPath(dyerConfiguration.getNvrWatchDirectory());
        System.out.println("Watching Path: " + pathToWatch.getFileName().toString());

        Thread thread = new Thread(new DirectoryWatcher(pathToWatch, true, new NVRFileEventService()));
        thread.start();

        for(;;) {
            System.out.println("Main thread sleeping ...");
            Thread.sleep(300000);
        }

    }
}
