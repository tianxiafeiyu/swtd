package com.txy.sw_demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.txy.sw_demo.bean.User;
import com.txy.sw_demo.service.dao.impl.MysqlUserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 使用原生 jdbc 操作数据库
 * @Auther: tianxiayu
 * @Date: 2020/11/3 16:22
 */
@Service
public class MysqlTestService {
    @Autowired
    private MysqlUserDAOImpl mysqlUserDAO;

    public Object add(String jsonData) {
        JSONObject object = JSON.parseObject(jsonData);
        User user = new User(object.getString("name"), object.getString("age"));
        return mysqlUserDAO.add(user);
    }

    public  Object list() {
        return mysqlUserDAO.list();
    }

    public Object delete(String jsonData) {
        JSONObject object = JSON.parseObject(jsonData);
        User user = new User(object.getString("name"), object.getString("age"));
        return mysqlUserDAO.delete(user);
    }


}