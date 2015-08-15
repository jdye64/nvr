package com.jeremydyer.cli;

import com.jeremydyer.NVRConfiguration;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jeremydyer on 8/15/15.
 */
public class Solr
        extends ConfiguredCommand<NVRConfiguration> {

    final static Logger logger = LoggerFactory.getLogger(NVR.class);

    private String urlString = "http://10.0.1.17:8983/solr/techproducts";
    private SolrClient solr = new HttpSolrClient(urlString);

    public Solr(String name, String description) {
        super(name, description);
    }

    @Override
    protected void run(Bootstrap<NVRConfiguration> nvrConfigurationBootstrap, Namespace namespace, NVRConfiguration nvrConfiguration) throws Exception {
        System.out.println("Testing some solr commands here");

        try {
            //Index the event to Solr for online viewing.
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", "552199");
            document.addField("name", "Gouda cheese wheel");
            document.addField("price", "49.99");
            UpdateResponse response = solr.add(document);

            solr.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}