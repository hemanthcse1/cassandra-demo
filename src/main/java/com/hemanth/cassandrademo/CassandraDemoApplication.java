package com.hemanth.cassandrademo;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CassandraDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CassandraDemoApplication.class, args);

        System.out.println("Cassandra demo");
    }

    @Autowired
    private CqlSession cqlSession;

    public void checkConnection() {
        SimpleStatement statement = SimpleStatement.builder("SELECT * FROM my_keyspace.my_table LIMIT 1").build();
        ResultSet resultSet = cqlSession.execute(statement);
        Row row = resultSet.one();
        if (row != null) {
            System.out.println("Connection to Cassandra is successful. Data retrieved: " + row.toString());
        } else {
            System.out.println("No data retrieved, but connection to Cassandra seems successful.");
        }
    }

}
