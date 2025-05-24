package com.dsalgado.apps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.dsalgado.apps",
    "com.dsalgado.schools",
    "com.dsalgado.backoffice",
    "com.dsalgado.sharedkernel"
})
@EntityScan(basePackages = {
    "com.dsalgado.schools",
    "com.dsalgado.backoffice"
})
@EnableJpaRepositories(basePackages = {
    "com.dsalgado.schools",
    "com.dsalgado.backoffice"
})
public class SchoolCommunicationApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchoolCommunicationApplication.class, args);
    }
} 