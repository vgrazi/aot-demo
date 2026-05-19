package com.vgrazi.cooljava25.aotdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.Instant;

@SpringBootApplication
public class AotDemoApplication implements CommandLineRunner {
    private static final Instant APP_START_TIME = Instant.now();
    public static void main(String[] args) {
        SpringApplication.run(AotDemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Duration timeSinceStart = Duration.between(APP_START_TIME, Instant.now());
        System.out.println("Application fully initialized in: " + timeSinceStart.toMillis() + " ms");

    }
}
