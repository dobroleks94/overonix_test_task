package com.overonix.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableFeignClients
public class TestOveronixApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestOveronixApplication.class, args);
    }

}
