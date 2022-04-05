package com.hjd.Execption;

import com.hjd.data.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 胡建德
 * @Date created in 14:06 2021/1/21
 * @Description: 全局异常捕获
 */
@Log4j2
@ControllerAdvice
public class ExceptionController {

    /**
     * 未知系统错误代码
     * @Author : 胡建德
     * @Date: 2021/1/26 16:59
     */
    private static final String SYS_ERROR = "99";

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result.Data dealException(Exception e){
        e.printStackTrace();
        log.error("系统错误：" + e);
        return Result.errorResultData(SYS_ERROR, "未知错误");
    }

}
