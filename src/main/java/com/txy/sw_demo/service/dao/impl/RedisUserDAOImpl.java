package com.txy.sw_demo.service.dao.impl;

import com.alibaba.fastjson.JSON;
import com.txy.sw_demo.bean.User;
import com.txy.sw_demo.service.dao.UserDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Auther: tianxiayu
 * @Date: 2020/11/6 14:56
 */
@Component
public class RedisUserDAOImpl implements UserDAO {
    @Value("${monitor.redis.address}")
    private String redisAddress;

    private String hashKey = "user";

    private Jedis defaultClient(){
        String[] strs = redisAddress.split(":");
        String ip = strs[0];
        int port = Integer.valueOf(strs[1]);

        return new Jedis(ip, port);
    }

    @Override
    public Object add(User user) {
        try(Jedis jedis = defaultClient())
        {
            jedis.hset(hashKey, user.getName(), JSON.toJSONString(user));
        }

        return user;
    }

    @Override
    public Object get(User user) {
        String jsonSting = "";
        try(Jedis jedis = defaultClient()) {
            jsonSting = jedis.hget(hashKey, user.getName());
        }
        return jsonSting;
    }

    @Override
    public Object list() {
        Set<String> keys = defaultClient().hkeys(hashKey);
        List<String> userList = new ArrayList<>();
        try (Jedis jedis = defaultClient()){
            for(String key : keys){
                userList.add(jedis.hget(hashKey, key));
            }
        }

        return userList;
    }

    @Override
    public Object delete(User user) {
        try(Jedis jedis = defaultClient()) {
            jedis.hdel(hashKey, user.getName());
        }
        return "OK";
    }
}