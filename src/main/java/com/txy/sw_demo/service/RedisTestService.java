package com.txy.sw_demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.txy.sw_demo.bean.User;
import com.txy.sw_demo.service.dao.impl.RedisUserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: tianxiayu
 * @Date: 2020/11/6 14:55
 */
@Service
public class RedisTestService {
    @Autowired
    private RedisUserDAOImpl redisUserDAO;

    public Object add(String jsonData) {
        JSONObject object = JSON.parseObject(jsonData);
        User user = new User(object.getString("name"), object.getString("age"));
        return redisUserDAO.add(user);
    }

    public Object delete(String jsonData) {
        JSONObject object = JSON.parseObject(jsonData);
        User user = new User(object.getString("name"), object.getString("age"));
        return redisUserDAO.delete(user);
    }

    public Object list() {
        return redisUserDAO.list();
    }
}