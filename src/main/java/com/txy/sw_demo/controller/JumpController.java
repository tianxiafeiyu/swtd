package com.txy.sw_demo.controller;

import com.txy.sw_demo.util.ControllerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制页面跳转
 *
 * @Auther: tianxiayu
 * @Date: 2020/11/3 13:51
 */
@Controller
public class JumpController {
    /**
     * 跳转首页
     */
    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        ControllerUtil.setLastUrl(request, response);
        return "index";
    }

    @GetMapping("/kafka")
    public String kafka(HttpServletRequest request, HttpServletResponse response) {
        ControllerUtil.setLastUrl(request, response);
        return "kafka";
    }

    @GetMapping("/mysql")
    public String mysql(HttpServletRequest request, HttpServletResponse response) {
        ControllerUtil.setLastUrl(request, response);
        return "mysql";
    }

    @GetMapping("/redis")
    public String redis(HttpServletRequest request, HttpServletResponse response) {
        ControllerUtil.setLastUrl(request, response);
        return "redis";
    }

    @GetMapping("/elasticsearch")
    public String elasticsearch(HttpServletRequest request, HttpServletResponse response) {
        ControllerUtil.setLastUrl(request, response);
        return "elasticsearch";
    }


}