package com.jeremydyer.cli;

import com.jeremydyer.NVRConfiguration;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

/**
 * Created by jeremydyer on 3/26/15.
 */
public class Bonjour extends ConfiguredCommand<NVRConfiguration> {

    final static Logger logger = LoggerFactory.getLogger(Bonjour.class);

    public Bonjour(String name, String description) {
        super(name, description);
    }

    @Override
    protected void run(Bootstrap<NVRConfiguration> nvrConfigurationBootstrap, Namespace namespace, NVRConfiguration nvrConfiguration) throws Exception {
        //Create the Bonjour network listener.
        JmDNS jmDns = JmDNS.create();

        jmDns.addServiceListener("_airplay._tcp.local.", new ServiceListener() {
            @Override
            public void serviceAdded(ServiceEvent serviceEvent) {

            }

            @Override
            public void serviceRemoved(ServiceEvent serviceEvent) {

            }

            @Override
            public void serviceResolved(ServiceEvent evt) {

                System.out.println();
                System.out.println("Type: " + evt.getType());
                System.out.println("Name: " + evt.getName());
                System.out.println("IP: " + evt.getInfo().getHostAddresses()[0]);
                System.out.println("Port: " + evt.getInfo().getPort());
                System.out.println("Name: " + evt.getInfo().getName());
                System.out.println("Desc: " + evt.getInfo().getNiceTextString());
                System.out.println("Entry Point: " + evt.getInfo().getURLs()[0]);
                System.out.println();

            }
        });
    }
}
