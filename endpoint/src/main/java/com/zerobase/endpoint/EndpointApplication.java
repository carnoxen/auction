package com.zerobase.endpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zerobase")
public class EndpointApplication {
    public static void main(String[] args) {
        SpringApplication.run(EndpointApplication.class, args);
    }
}
