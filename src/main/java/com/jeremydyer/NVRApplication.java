package com.jeremydyer;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.jeremydyer.cli.*;
import com.jeremydyer.resource.VideoStream;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

/**
 * Network Video Recorder (NVR)
 *
 * Created by jeremydyer on 3/13/15.
 */
public class NVRApplication extends Application<NVRConfiguration> {

    public static void main(String[] args) throws Exception {
        new NVRApplication().run(args);
    }

    @Override
    public String getName() {
        return "Network Video Recorder";
    }

    @Override
    public void initialize(Bootstrap<NVRConfiguration> bootstrap) {
        bootstrap.addCommand(new NVR("NVR", "Network Video Recorder Full Stack"));
        bootstrap.addCommand(new NVRScheduleRecorder("NVRScheduleRecorder", "Records video from IP cameras based on a time schedule"));
        bootstrap.addCommand(new NVRStorageGrimReaper("NVRStorageGrimReaper", "Reclaims storage on the main storage device"));
        bootstrap.addCommand(new NVRBatchBuildVideoIndex("NVRBatchBuildVideoIndex", "Examines all of the current video files and rebuilds the video index database"));
    }

    @Override
    public void run(NVRConfiguration configuration,
                    Environment environment) {
        final VideoStream resource = new VideoStream();
        environment.jersey().register(resource);

        MetricRegistry metricsRegistry = new MetricRegistry();
        final JmxReporter reporter = JmxReporter.forRegistry(metricsRegistry).build();
        reporter.start();

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
    }
}
