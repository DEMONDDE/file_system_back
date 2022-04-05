package com.hjd.Enmu;



/**
 * @author hujiande
 */

public enum ErrorEnum {
    SYSTEM_ERROR("99", "系统错误");

    private String code;
    private String msg;


    ErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
