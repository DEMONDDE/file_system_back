package com.hjd.Enmu;

/**
 * @author 胡建德
 * @Date created in 14:13 2021/1/21
 * @Description:
 */
public enum ResultEnum {
    SUCCESS("00", "成功"),
    ERROR("01","失败");

    private String code;
    private String msg;

    ResultEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
