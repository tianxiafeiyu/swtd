package com.txy.sw_demo.service.dao.impl;

import com.txy.sw_demo.bean.User;
import com.txy.sw_demo.service.dao.UserDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    Connection con = null;		//连接
    PreparedStatement pstmt = null;	//使用预编译语句
    ResultSet rs = null;	//获取的结果集

    private void innit(){
        try {
            Class.forName(driverClassName); //执行驱动
            con = DriverManager.getConnection(url, username, password); //获取连接
            String sql = "INSERT INTO USER VALUES(?,?,?,?)"; //设置的预编译语句格式
            pstmt = con.prepareStatement(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        // 释放资源
        try {
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(con != null) con.close();  //必须要关
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeQuery(String sql){
        try {
            pstmt.execute(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createTable(){
        this.innit();

        String sql = "create table ";
    }

    public void insertData(){
        this.innit();
    }
    public void queryData(){
        this.innit();
    }

    @Override
    public Object add(User user) {
        return null;
    }

    @Override
    public Object get(User user) {
        return null;
    }

    @Override
    public Object list() {
        return null;
    }

    @Override
    public Object delete(User user) {
        return null;
    }
}