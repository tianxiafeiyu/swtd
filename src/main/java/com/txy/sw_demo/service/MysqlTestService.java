package com.txy.sw_demo.service;

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
    private MysqlUserDAOImpl mysqlDao;

    public void executeQuery(String sql) {
        mysqlDao.executeQuery(sql);
    }

}