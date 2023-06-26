package com.lujun61.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 封装返回给前端的通用json数据串
 * @author Jun Lu
 * @date 2023-06-26 10:06:27
 */
@Data   //set/get方法
@AllArgsConstructor //有参构造器
@NoArgsConstructor  //无参构造器
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data; //泛型，对应类型的json数据

    // 自定义两个参数的构造方法
    public CommonResult(Integer code, String message){
        this(code, message, null);
    }
}