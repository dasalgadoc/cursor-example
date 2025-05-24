package com.dsalgado.apps.backoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.dsalgado.apps.backoffice",
    "com.dsalgado.backoffice",
    "com.dsalgado.sharedkernel"
})
@EntityScan(basePackages = {
    "com.dsalgado.backoffice"
})
@EnableJpaRepositories(basePackages = {
    "com.dsalgado.backoffice"
})
public class BackofficeApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackofficeApiApplication.class, args);
    }
} 