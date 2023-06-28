package com.lujun61;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// 声明为Feign的客户端
@EnableFeignClients
public class OrderOpenFeign8080 {

    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeign8080.class, args);
    }

}
