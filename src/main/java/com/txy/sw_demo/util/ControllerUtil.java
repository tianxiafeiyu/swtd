package com.txy.sw_demo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制层用工具类
 * @Auther: tianxiayu
 * @Date: 2020/11/4 13:44
 */
public class ControllerUtil {
    /**
     * 设置回跳 url 静态方法
     * @param request
     * @param response
     */
    public static void setLastUrl(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        //判断是否有需要回跳的url，有则将需要回跳的url保存在响应头中
        response.setHeader("lastUrl", (String) request.getSession().getAttribute("lastUrl"));
    }
}