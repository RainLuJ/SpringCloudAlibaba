package com.lujun61.controller;

import com.lujun61.entity.CommonResult;
import com.lujun61.entity.Payment;
import com.lujun61.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PaymentFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    /**
     * @description 测试使用OpenFeign调用服务
     * @author Jun Lu
     * @date 2023-06-27 16:25:12
     */
    @GetMapping("/customer/feign/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    /**
     * @description 测试使用OpenFeign调用超时服务
     * @author Jun Lu
     * @date 2023-06-27 16:25:41
     */
    // 消费方80
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        /*
            OpenFeign客户端在调用服务时一般默认等待1秒钟；
            超时后直接抛出异常！
        */
        return paymentFeignService.paymentFeignTimeout();
    }


}
