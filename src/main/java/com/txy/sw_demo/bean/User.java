package com.txy.sw_demo.bean;

/**
 * 用户 bean
 * @Auther: tianxiayu
 * @Date: 2020/11/5 10:15
 */
public class User {

    private String name;

    private String age;

    public User(String name, String age){
        this.name = name;
        this.age = age;
    }

    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}