package com.txy.sw_demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.txy.sw_demo.bean.User;
import com.txy.sw_demo.service.dao.impl.EsUserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: tianxiayu
 * @Date: 2020/11/4 14:33
 */
@Service
public class EsTestService {
    @Autowired
    private EsUserDAOImpl esUserDAO;

    public Object index(String jsonData) {
        JSONObject object = JSON.parseObject(jsonData);
        User user = new User(object.getString("name"), object.getString("age"));
        return esUserDAO.add(user);
    }

    public Object get(String jsonData) {
        return null;
    }

    public Object update(String jsonData) {
        return null;
    }

    public Object delete(String jsonData) {
        JSONObject object = JSON.parseObject(jsonData);
        User user = new User(object.getString("name"), object.getString("age"));
        return esUserDAO.delete(user);
    }

    public Object list() {
        return esUserDAO.list();
    }
}