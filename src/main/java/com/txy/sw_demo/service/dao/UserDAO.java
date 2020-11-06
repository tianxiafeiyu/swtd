package com.txy.sw_demo.service.dao;

import com.txy.sw_demo.bean.User;

/**
 * @Auther: tianxiayu
 * @Date: 2020/11/5 10:10
 */
public interface UserDAO {
    public Object add(User user);

    public Object get(User user);

    public Object list();

    public Object delete(User user);
}
