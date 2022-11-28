package com.laity.store.controller;

import com.laity.store.entity.User;
import com.laity.store.service.IUserService;
import com.laity.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<>(OK, "用户注册成功");
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password) {
        User data = userService.login(username, password);
        return new JsonResult<>(OK, "用户登录成功", data);
    }
}
