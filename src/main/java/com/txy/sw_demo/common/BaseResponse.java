package com.txy.sw_demo.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 统一响应体注解，接口添加注解后生效
 * @Auther: tianxiayu
 * @Date: 2020/11/3 15:58
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface BaseResponse {
}
