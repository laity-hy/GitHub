package com.laity.store.controller;

import com.laity.store.service.ex.InsertException;
import com.laity.store.service.ex.ServiceException;
import com.laity.store.service.ex.UsernameDuplicatedException;
import com.laity.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
    /**
     * 操作成功的状态码
     */
    public static final int OK = 200;

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("用户名已被占用");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        }
        return result;
    }
}
