package com.txy.sw_demo.controller;

import com.txy.sw_demo.service.EsTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: tianxiayu
 * @Date: 2020/11/4 14:32
 */
@RestController
@RequestMapping("/es")
public class EsTestController {
    @Autowired
    private EsTestService esTestService;

    @RequestMapping("/index")
    public Object index(@RequestBody String jsonData){
        return  esTestService.index(jsonData);
    }

    @RequestMapping("/get")
    public Object get(String jsonData){
        esTestService.get(jsonData);
        return null;
    }

    @RequestMapping("/update")
    public Object update(String jsonData){
        esTestService.update(jsonData);
        return null;
    }

    @RequestMapping("/delete")
    public Object delete(String jsonData){
        esTestService.delete(jsonData);
        return null;
    }

    @RequestMapping("/list")
    public Object list(){
        return esTestService.list();
    }

}