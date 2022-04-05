package com.hjd.Execption;

/**
 * @author 胡建德
 * @Date created in 18:11 2021/1/21
 * @Description: service层通用异常
 */
public class ServiceException extends RuntimeException{

    public ServiceException(){

    }

    public ServiceException(String msg){
        super(msg);
    }
}
