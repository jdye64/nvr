package com.jeremydyer.cli;

import com.jeremydyer.NVRConfiguration;
import com.jeremydyer.service.nvr.storage.StorageManager;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jeremydyer on 3/19/15.
 */
public class NVRStorageGrimReaper
        extends ConfiguredCommand<NVRConfiguration> {

    final static Logger logger = LoggerFactory.getLogger(NVRStorageGrimReaper.class);

    public NVRStorageGrimReaper(String name, String description) {
        super(name, description);
    }

    @Override
    protected void run(Bootstrap<NVRConfiguration> dyerConfigurationBootstrap, Namespace namespace, NVRConfiguration dyerConfiguration) throws Exception {
        System.out.println("Running NVRStorageGrimReaper service ... still isn't implemented");

        StorageManager sm = new StorageManager(dyerConfiguration);
        sm.archiveFiles();
    }
}