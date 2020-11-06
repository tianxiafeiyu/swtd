package com.txy.sw_demo.controller;

import com.txy.sw_demo.service.MysqlTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: tianxiayu
 * @Date: 2020/11/3 16:21
 */
@RestController
@RequestMapping("/mysql")
public class MysqlTestController {
    @Autowired
    private MysqlTestService mysqlTestService;

    public Object insert(){

        return null;
    }

    public Object query(){

        return null;
    }

    @RequestMapping("/execute_query")
    public Object executeQuery(String sql){
        mysqlTestService.executeQuery(sql);
        return null;
    }

}