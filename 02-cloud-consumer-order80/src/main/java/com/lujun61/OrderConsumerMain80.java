package com.lujun61;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrderConsumerMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderConsumerMain80.class, args);
    }

}
