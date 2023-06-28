package com.lujun61;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// 扫描声明为Feign客户端的接口
@EnableFeignClients
// 启用Hystrix断路器
// @EnableHystrix
@EnableCircuitBreaker
public class OrderHystrixMain8080 { //消费端

    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain8080.class, args);
    }
}
