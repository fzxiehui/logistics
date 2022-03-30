package com.fdzc.controller.exception;

import com.fdzc.utils.Result;
import org.apache.shiro.ShiroException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 异常处理
 * @Date 2018-04-09
 * @Time 17:09
 */
@RestControllerAdvice
public class ExceptionController {
//    private final Result result;
//
//    @Autowired
//    public ExceptionController(Result result) {
//        this.result = result;
//    }

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public Result handle401() {
        return new Result(401,"您没有权限访问",null);
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public Result globalException(HttpServletRequest request, Throwable ex) {
        return new Result(getStatus(request).value(),"访问出错，无法访问: " + ex.getMessage(),null);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
