package com.hemanth.cassandrademo;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.metadata.Metadata;
import com.datastax.oss.driver.api.core.metadata.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CassandraConnectionCheck implements CommandLineRunner {

    @Autowired
    private CqlSession cqlSession;

    @Override
    public void run(String... args) throws Exception {
        Metadata metadata = cqlSession.getMetadata();
        System.out.println("Connected to Cassandra cluster: " + metadata.getClusterName());
        for (Node node : metadata.getNodes().values()) {
            System.out.printf("DataCenter: %s; Rack: %s; Host: %s%n",
                    node.getDatacenter(),
                    node.getRack(),
                    node.getBroadcastAddress().get());
        }
    }
}
