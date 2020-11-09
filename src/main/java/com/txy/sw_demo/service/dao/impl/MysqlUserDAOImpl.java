package com.txy.sw_demo.service.dao.impl;

import com.alibaba.fastjson.JSON;
import com.txy.sw_demo.bean.User;
import com.txy.sw_demo.service.dao.UserDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: tianxiayu
 * @Date: 2020/11/3 17:04
 */
@Component
public class MysqlUserDAOImpl implements UserDAO {
    @Value("${monitor.mysql.driver-class-name}")
    private String driverClassName;
    @Value("${monitor.mysql.url}")
    private String url;
    @Value("${monitor.mysql.username}")
    private String username;
    @Value("${monitor.mysql.password}")
    private String password;

    private Connection con = null;		//连接
    private PreparedStatement pstmt = null;	//使用预编译语句
    private ResultSet rs = null;	//获取的结果集

    private Connection defaultConnection(){
        try {
            if(con == null || con.isClosed()){
                Class.forName(driverClassName);
                con = DriverManager.getConnection(url, username, password);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }



    @Override
    public Object add(User user) {
        String sql = "insert into user values(?, ?)";
        try {
            PreparedStatement pstm = defaultConnection().prepareStatement(sql);
            pstm.setString(1, user.getName());
            pstm.setString(2, user.getAge());
            pstm.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Object get(User user) {
        return null;
    }

    @Override
    public Object list() {
        List<String> userList = new ArrayList<>();
        String sql = "select * from user";
        try {
            PreparedStatement pstm = defaultConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                userList.add(JSON.toJSONString(rs));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public Object delete(User user) {
        String sql = "delete from user where name = ?";
        try {
            PreparedStatement pstm = defaultConnection().prepareStatement(sql);
            pstm.setString(1, user.getName());
            pstm.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}