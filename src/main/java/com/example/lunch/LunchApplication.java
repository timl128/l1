package com.example.lunch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LunchApplication {

    public static void main(String[] args) {
        SpringApplication.run(LunchApplication.class, args);
    }

}

