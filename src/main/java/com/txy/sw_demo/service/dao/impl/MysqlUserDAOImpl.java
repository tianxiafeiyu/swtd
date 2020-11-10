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

    private Connection defaultConnection(){
        Connection connection = null;
        try {
            Class.forName(driverClassName);
            return DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }



    @Override
    public Object add(User user) {
        String sql = "insert into user values(?, ?)";
        try (
            Connection connection = defaultConnection();
            PreparedStatement pstm = connection.prepareStatement(sql)
        ) {
            pstm.setString(1, user.getName());
            pstm.setString(2, user.getAge());
            pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public Object add1(User user) {
        String sql = "insert into user values(?, ?)";
        Connection connection = null;
        PreparedStatement pstm = null;
        try{
            connection = defaultConnection();
            pstm = connection.prepareStatement(sql);

            pstm.setString(1, user.getName());
            pstm.setString(2, user.getAge());
            pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(pstm != null){
                try {
                    pstm.close();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(connection != null){
                        try {
                            connection.close();
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
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
        try (
                Connection connection = defaultConnection();
                PreparedStatement pstm = connection.prepareStatement(sql)
        ) {
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
        try (
                Connection connection = defaultConnection();
                PreparedStatement pstm = connection.prepareStatement(sql)
        ) {
            pstm.setString(1, user.getName());
            pstm.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}