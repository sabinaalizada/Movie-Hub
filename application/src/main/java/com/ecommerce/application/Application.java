package com.ecommerce.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "com.ecommerce.moviecore.repository.mongo")
@ComponentScan(basePackages = "com.ecommerce")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
