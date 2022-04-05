package com.hjd.data;

import com.hjd.Enmu.ResultEnum;

import java.io.Serializable;

/**
 * @author 胡建德
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 8094972659465448521L;

    public static class Data {

        private String retCode;

        private String retMsg;

        private Object data;


        Data(String retCode, String retMsg, Object data) {
            this.retCode = retCode;
            this.retMsg = retMsg;
            this.data = data;
        }

        Data(String retCode, String retMsg) {
            this.retCode = retCode;
            this.retMsg = retMsg;
        }

        public Data() {

        }

        public String getRetCode() {
            return retCode;
        }

        public void setRetCode(String retCode) {
            this.retCode = retCode;
        }

        public String getRetMsg() {
            return retMsg;
        }

        public void setRetMsg(String retMsg) {
            this.retMsg = retMsg;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

    public static Data successResultData(Object data) {
        return new Data(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), data);
    }

    public static Data successResultData(String retMsg, Object data) {
        return new Data(ResultEnum.SUCCESS.getCode(), retMsg, data);
    }

    public static Data successResultData(String retCode, String retMsg, Object data) {
        return new Data(retCode, retMsg, data);
    }

    public static Data errorResultData() {
        return new Data(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
    }

    public static Data errorResultData(String retMsg) {
        return new Data(ResultEnum.ERROR.getCode(), retMsg);
    }

    public static Data errorResultData(String retCode, String retMsg) {
        return new Data(retCode, retMsg);
    }
}
