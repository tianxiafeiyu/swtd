package com.txy.sw_demo.common;

/**
 * 状态码枚举
 * @Auther: tianxiayu
 * @Date: 2020/11/3 16:00
 */
public enum ResponseCode {
    /**
     * 成功返回的状态码
     */
    SUCCESS(200, "success");

    private int code;

    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
