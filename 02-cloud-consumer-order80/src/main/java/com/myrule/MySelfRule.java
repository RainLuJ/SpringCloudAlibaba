package com.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description 单独为Ribbon进行配置的配置类。
 *              这里是为Ribbon配置了LoadBalance算法为：随机访问
 * @author Jun Lu
 * @date 2023-06-27 11:38:48
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        // 定义LB的方式为：随机访问
        return new RandomRule();
    }

}
