package com.txy.sw_demo.controller;

import com.txy.sw_demo.service.RedisTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: tianxiayu
 * @Date: 2020/11/6 14:55
 */
@RestController
@RequestMapping("/redis")
public class RedisTestController {
    @Autowired
    private RedisTestService redisTestService;

    @RequestMapping("/add")
    public Object index(@RequestBody String jsonData){
        return  redisTestService.add(jsonData);
    }

    @RequestMapping("/list")
    public Object list(){
        return redisTestService.list();
    }

    @RequestMapping("/delete")
    public Object delete(@RequestBody String jsonData){
        return redisTestService.delete(jsonData);
    }

}