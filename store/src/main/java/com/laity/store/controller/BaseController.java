package com.laity.store.controller;

import com.laity.store.service.ex.*;
import com.laity.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {
    /**
     * 操作成功的状态码
     */
    public static final int OK = 200;

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException) {//reg
            result.setState(4000);
            result.setMessage("用户名已被占用异常");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("在用户注册过程中产生了未知的异常");
        } else if (e instanceof UserNotFoundException) {//login
            result.setState(5001);
            result.setMessage("用户数据不存在异常");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("用户密码错误异常");
        }
        return result;
    }

    /**
     * 获取session对象中的uid
     *
     * @param session session对象
     * @return 当前登录的用户uid的值
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录用户的username
     *
     * @param session session对象
     * @return 当前登录的用户名
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
