package com.lujun61.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    // 使用LoadBalanced注解开启RestTemplate的负载均衡调用功能
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
