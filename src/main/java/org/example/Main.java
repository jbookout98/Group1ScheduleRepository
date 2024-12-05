package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableScheduling
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Initialize Firebase after the Spring context is initialized
    @PostConstruct
    public void init() {
        try {
            new FirebaseConfig().initialize();  // Initialize Firebase
            logger.info("Firebase initialization successful.");
        } catch (IOException e) {
            logger.error("Error initializing Firebase", e);
        }
    }
}
