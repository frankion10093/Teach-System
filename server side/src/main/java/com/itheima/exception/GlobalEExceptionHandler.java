package com.itheima.exception;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalEExceptionHandler {

    @ExceptionHandler(Exception.class)//捕获所有异常
    public Result ex(Exception ex){
        ex.printStackTrace();//输出异常的堆栈信息
        return Result.error("发生问题，请联系管理员");
    }
}
