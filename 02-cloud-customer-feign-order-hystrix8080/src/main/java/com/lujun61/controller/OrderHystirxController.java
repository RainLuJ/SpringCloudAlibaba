package com.lujun61.controller;

import com.lujun61.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
/* 配置全局fallback处理方法：单独指定了兜底方法的方法会走自己的兜底方法，否则就走全局配置的兜底方法 */
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
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
    @HystrixCommand(fallbackMethod = "paymentOKFallbackMethod",
            commandProperties = {
                    /* 消费端只等1.5s */
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")})
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    public String paymentOKFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试 或者 自己运行出错请检查自己,o(╥﹏╥)o（1）";
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",
            commandProperties = {
                                                                                            /* 消费端只等1.5s */
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")})
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试 或者 自己运行出错请检查自己,o(╥﹏╥)o（2）";
    }

    @GetMapping("/consumer/payment/hystrix/exception/{id}")
    @HystrixCommand(fallbackMethod = "paymentExceptionFallbackMethod",
            commandProperties = {
                                                                                            /* 消费端只等1.5s */
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")})
    public String paymentInfo_Exception(@PathVariable("id") Integer id) {
        int i = 10 / 0;

        return paymentHystrixService.paymentInfo_Exception(id);
    }

    public String paymentExceptionFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试 或者 自己运行出错请检查自己,o(╥﹏╥)o（3）";
    }

    // TODO: 定义全局fallback方法
    /* 单独指定了兜底方法的方法会走自己的兜底方法，否则就走全局配置的兜底方法 */
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}

