package com.sparkdigital.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * This is the stand alone service.
 */
@SpringBootApplication
@EnableNeo4jRepositories("com.sparkdigital.api.repositories")
public class SparklyMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(SparklyMovieApplication.class, args);
    }
}