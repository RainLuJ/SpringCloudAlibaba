package com.lujun61;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.lujun61.dao")
// @EnableEurekaClient
/* @EnableDiscoveryClient 是对 @EnableEurekaClient 的升级 */
@EnableDiscoveryClient
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }

    /**
     * @description 注入豪猪的servlet：该servlet与服务容错本身无关，是SpringCloud升级后的坑。
     * 因为SpringBoot的默认路径不是/hustrix.stream，所以需要在自己的项目里配置一下servlet。
     * @author Jun Lu
     * @date 2023-06-28 18:40:18
     */
    // TODO: SpringCloud升级后的坑
    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<HystrixMetricsStreamServlet> servletRegistrationBean
                = new ServletRegistrationBean<>(streamServlet);
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.addUrlMappings("/hystrix.stream");
        servletRegistrationBean.setName("HystrixMetricsStreamServlet");

        return servletRegistrationBean;
    }

}
