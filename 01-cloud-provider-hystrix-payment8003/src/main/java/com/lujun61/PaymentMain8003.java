package com.lujun61;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
/*
    @EnableHystrix注解被@EnableCircuitBreaker了，
    @EnableCircuitBreaker的作用是开启熔断器功能。
    且@EnableCircuitBreaker支持对多种熔断器的集成，不仅仅是Hystrix，还有Resilience4j等……
 */
//@EnableHystrix
@EnableCircuitBreaker
public class PaymentMain8003 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8003.class, args);
    }
}

