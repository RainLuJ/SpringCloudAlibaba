package com.lujun61.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     * @description 可以正常访问的方法
     * @author Jun Lu
     * @date 2023-06-28 09:47:52
     */
    public String paymentinfo_Ok(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "--paymentInfo_OK，id:" + id;
    }

    /**
     * @description （1）模拟【超时访问】而触发熔断，进而调用兜底方法对用户进行响应
     * 要想正确使用这个方法，需要在主启动类上加上@EnableCircuitBreaker注解或者@EnableHystrix注解
     * @author Jun Lu
     * @date 2023-06-28 09:47:34
     */
    /*                                  指定兜底方法 */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",
            commandProperties = {
                    /*                                      时间单位                                超时时间 */
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            }
    )
    public String paymentinfo_Timeout(Integer id) {
        int interTime = 5;
        try {
            TimeUnit.SECONDS.sleep(interTime);//模拟超时
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "--paymentInfo_Timeout，id:" + id +
                "耗时" + interTime + "秒钟--";
    }

    /**
     * @description （1.1）兜底方法：回调函数向调用方返回一个符合预期的、可处理的备选响应
     * @author Jun Lu
     * @date 2023-06-28 09:49:22
     */
    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池:  " + Thread.currentThread().getName() + "  8001系统繁忙或者运行报错，请稍后再试,id:  " + id + "\t" + "o(╥﹏╥)o";
    }


    /**
     * @description （2）模拟【出现异常】而触发熔断，进而调用兜底方法对用户进行响应
     * @author Jun Lu
     * @date 2023-06-28 10:03:08
     */
    /*                                  指定兜底方法 */
    @HystrixCommand(fallbackMethod = "paymentInfo_ExceptionHandler",
            commandProperties = {
                    /*                                      时间单位                                超时时间 */
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            }
    )
    public String paymentInfo_Exception(Integer id) {
        int age = 10 / 0;
        return "线程池:  " + Thread.currentThread().getName() + " id:  " + id + "\t" + "O(∩_∩)O哈哈~" + "  耗时(秒): ";
    }

    /**
     * @description （2.1）兜底方法：回调函数向调用方返回一个符合预期的、可处理的备选响应
     * @author Jun Lu
     * @date 2023-06-28 09:49:22
     */
    public String paymentInfo_ExceptionHandler(Integer id) { // 回调函数向调用方返回一个符合预期的、可处理的备选响应
        return "线程池:  " + Thread.currentThread().getName() + "  8001系统繁忙或者运行报错，请稍后再试,id:  " + id + "\t" + "o(╥﹏╥)o";
    }

    /**
     * @description 服务熔断的实现
     * @author Jun Lu
     * @date 2023-06-28 11:32:29
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",
            commandProperties = {
            /* 配置说明：在10s内的10次请求中如果有60%的请求是失败的，则产生服务熔断 */
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
            })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        // hutool-all.jar包中的工具类方法
        // 等价于UUID.randomUUID().toString(); //pom中有
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id) {//服务降级
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }
}

