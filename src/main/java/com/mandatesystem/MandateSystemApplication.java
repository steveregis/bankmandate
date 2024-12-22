package com.mandatesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.mandatesystem.repository")
@EntityScan(basePackages = "com.mandatesystem.domain") // Add this line
@EnableRetry
public class MandateSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(MandateSystemApplication.class, args);
    }
}
