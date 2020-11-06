package com.txy.sw_demo.controller;

import com.txy.sw_demo.service.KafkaTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Auther: tianxiayu
 * @Date: 2020/11/2 15:23
 */
@RestController
@RequestMapping("/kafka")
public class KafkaTestController {
    @Autowired
    private KafkaTestService kafkaTestService;

    @RequestMapping("/produce")
    public Object produce(@RequestBody Map<String, String> reqMap){
        kafkaTestService.produce(reqMap.get("topic"), reqMap.get("msg"));
        return null;
    }

    @RequestMapping("/consume")
    public String consume(@RequestParam(value = "topic")String topic ){
        return kafkaTestService.consume(topic);
    }
}