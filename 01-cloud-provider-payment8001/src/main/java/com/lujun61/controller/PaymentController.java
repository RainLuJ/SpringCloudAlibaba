package com.lujun61.controller;

import com.lujun61.entity.CommonResult;
import com.lujun61.entity.Payment;
import com.lujun61.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentServiceImpl;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    /*
        是import org.springframework.cloud.client.discovery.DiscoveryClient;
        而不是import com.netflix.discovery.DiscoveryClient;
    */

    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        Integer result = paymentServiceImpl.create(payment);
        log.info("*****插入结果：" + result);

        if (result > 0) {
            return new CommonResult<>(200, "插入数据库成功,serverPort: " + serverPort, result);
        } else {
            return new CommonResult<>(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentServiceImpl.getPaymentById(id);

        if (payment != null) {
            return new CommonResult<>(200, "查询成功,serverPort:  " + serverPort, payment);
        } else {
            return new CommonResult<>(444, "没有对应记录,查询ID: " + id, null);
        }
    }

    /**
     * @description 服务的注册与发现
     * @author Jun Lu
     * @date 2023-06-26 18:15:04
     */
    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        /*
            获取服务列表清单：
                即：网页上Eurake面板"Application"处的服务名
         */
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            // 例如："CLOUD-PAYMENT-SERVICE"
            String serviceName = "spring.application.name: " + service.toUpperCase();
            System.out.println(serviceName);

            /*
                根据服务名获取该服务名下的所有服务实例（重名的服务实例）
                    即：网页上Eurake面板"Status"处的服务实例信息
             */
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
            for (ServiceInstance instance : instances) {
                // 例如：服务名同为 “CLOUD-PAYMENT-SERVICE” 的 payment8001 与 payment8002 服务实例
                System.out.println("与<<" + serviceName + ">> 同名的服务实例：" + instance.getServiceId() + "\t"
                        + instance.getHost() + "\t"
                        + instance.getPort() + "\t"
                        + instance.getUri());
            }
        }

        return this.discoveryClient;
    }

    /**
     * @description 演示Feign的超时控制，在服务提供方写一个暂停3秒的逻辑程序
     * @author Jun Lu
     * @date 2023-06-27 16:23:07
     */
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // 业务逻辑处理正确，但是需要耗费3秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}

