package com.dsalgado.apps.schools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.dsalgado.apps.schools",
    "com.dsalgado.schools",
    "com.dsalgado.sharedkernel"
})
@EntityScan(basePackages = {
    "com.dsalgado.schools"
})
@EnableJpaRepositories(basePackages = {
    "com.dsalgado.schools"
})
public class SchoolsApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchoolsApiApplication.class, args);
    }
} 