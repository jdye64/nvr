package com.jeremydyer;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.jeremydyer.cli.*;
import com.jeremydyer.resource.VideoStream;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Network Video Recorder (NVR)
 *
 * Created by jeremydyer on 3/13/15.
 */
public class NVRApplication extends Application<NVRConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(NVRApplication.class);

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
    }

    @Override
    public void run(NVRConfiguration configuration,
                    Environment environment) {
        logger.info(NVRApplication.class.getName() + " Starting");
        final VideoStream resource = new VideoStream();
        environment.jersey().register(resource);

        MetricRegistry metricsRegistry = new MetricRegistry();
        final JmxReporter reporter = JmxReporter.forRegistry(metricsRegistry).build();
        reporter.start();
    }
}
