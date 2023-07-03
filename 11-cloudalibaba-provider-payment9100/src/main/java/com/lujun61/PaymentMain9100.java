package com.lujun61;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain9100 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9100.class, args);
    }
}

