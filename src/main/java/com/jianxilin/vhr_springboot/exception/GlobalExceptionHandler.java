package com.jianxilin.vhr_springboot.exception;

import com.jianxilin.vhr_springboot.model.ResponseBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;


/**
 * @author 54683
 */
//TODO 笔记2 全局异常处理
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLException.class)
    public ResponseBean sqlException(SQLException e){
        // e instanceof
        return ResponseBean.fail("数据库异常，操作失败");
    }
}
