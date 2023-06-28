package com.lujun61.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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

}

