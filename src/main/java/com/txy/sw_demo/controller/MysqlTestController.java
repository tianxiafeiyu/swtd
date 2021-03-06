package com.txy.sw_demo.controller;

import com.txy.sw_demo.service.MysqlTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping("/add")
    public Object index(@RequestBody String jsonData){
        return  mysqlTestService.add(jsonData);
    }

    @RequestMapping("/list")
    public Object list(){
        return mysqlTestService.list();
    }

    @RequestMapping("/delete")
    public Object delete(@RequestBody String jsonData){
        return mysqlTestService.delete(jsonData);
    }

}