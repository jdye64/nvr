package com.jeremydyer.cli;

import com.jeremydyer.NVRConfiguration;
import com.jeremydyer.service.nvr.index.VideoIndexManager;
import com.jeremydyer.service.nvr.storage.StorageManager;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;

/**
 * Examines all of the existing video files and rebuilds the index/database that holds all of the information
 * about all of the video files and is mostly used for keeping track of events and understanding how much storage has
 * been used, etc.
 *
 * Created by jeremydyer on 3/19/15.
 */
public class NVRBatchBuildVideoIndex
        extends ConfiguredCommand<NVRConfiguration> {

    final static Logger logger = LoggerFactory.getLogger(NVREventWatcher.class);

    public NVRBatchBuildVideoIndex(String name, String description) {
        super(name, description);
    }

    @Override
    protected void run(Bootstrap<NVRConfiguration> dyerConfigurationBootstrap, Namespace namespace, NVRConfiguration dyerConfiguration) throws Exception {
        StorageManager sm = new StorageManager(dyerConfiguration);
        List<Path> videoFiles = sm.listAllVideoFiles();
        VideoIndexManager vim = new VideoIndexManager(dyerConfiguration);
        vim.indexVideoFiles(videoFiles);
    }
}
