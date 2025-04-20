package com.phase1.springboot_basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Combines @Configuration, @EnableAutoConfiguration, @ComponentScan
public class SpringbootBasicsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootBasicsApplication.class, args);
    }

}

