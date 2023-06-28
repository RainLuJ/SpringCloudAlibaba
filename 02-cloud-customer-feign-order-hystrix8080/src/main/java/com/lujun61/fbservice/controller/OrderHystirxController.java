package com.lujun61.fbservice.controller;

import com.lujun61.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystirxController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    /**
     * @description 可正常访问的方法。在测试熔断的时候可以使用。
     *  具体使用：在浏览器疯狂发送对timeout|exception的方法的请求，触发熔断，
     *           再访问当前方法，会发现当前方法也存在短暂的无法访问的情况。
     * @author Jun Lu
     * @date 2023-06-28 10:45:32
     */
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/exception/{id}")
    public String paymentInfo_Exception(@PathVariable("id") Integer id) {
        int i = 10 / 0;

        return paymentHystrixService.paymentInfo_Exception(id);
    }
}

