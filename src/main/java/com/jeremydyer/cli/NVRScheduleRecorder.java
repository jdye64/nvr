package com.jeremydyer.cli;

import com.jeremydyer.NVRConfiguration;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Records IP camera video on a scheduled basis.
 *
 * Created by jeremydyer on 3/19/15.
 */
public class NVRScheduleRecorder
        extends ConfiguredCommand<NVRConfiguration> {

    final static Logger logger = LoggerFactory.getLogger(NVRScheduleRecorder.class);

    public NVRScheduleRecorder(String name, String description) {
        super(name, description);
    }

    @Override
    protected void run(Bootstrap<NVRConfiguration> dyerConfigurationBootstrap, Namespace namespace, NVRConfiguration dyerConfiguration) throws Exception {
        System.out.println("Running NVRScheduleRecorder service ... still isn't implemented");
    }
}
