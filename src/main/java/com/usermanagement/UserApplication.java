package com.usermanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.usermanagement")
public class UserApplication {

    public static final Logger logger = LoggerFactory.getLogger(UserApplication.class);

    public static void main(String[] args) {
        logger.info("Application started.");
        SpringApplication.run(UserApplication.class, args);
        logger.info("Application ended.");
    }

}
