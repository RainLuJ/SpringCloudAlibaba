package com.lujun61;

import com.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
// 负载均衡客户端：为在LB调用微服务名为"CLOUD-PAYMENT-SERVICE"的服务时配置自定义的负载均衡策略
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class)
public class OrderConsumerMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderConsumerMain80.class, args);
    }

}
