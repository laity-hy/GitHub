package com.laity.store.service.impl;

import com.laity.store.entity.User;
import com.laity.store.mapper.UserMapper;
import com.laity.store.service.IUserService;
import com.laity.store.service.ex.InsertException;
import com.laity.store.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * 用户模块业务层的实现类
 */
@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        //通过user参数来获取传递过来的username
        String username = user.getUsername();
        //调用findByUsername(String username)判断用户是否被注册过
        User result = userMapper.findByUsername(username);
        //判断结果集是否为null则抛出用户名被占用的异常
        if (result != null) {
            //抛出异常
            throw new UsernameDuplicatedException("用户名被占用");
        }

        //密码加密处理的实现：md5算法的实现
        String oldPassword = user.getPassword();
        //获取盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        //整体加密处理
        String newPassword = getMd5Password(oldPassword, salt);
        user.setPassword(newPassword);
        user.setSalt(salt);
        //补全数据：is_delete设置为0，4个日志数据
        user.setIsDelete(0);
        Date date = new Date();
        user.setCreatedUser(user.getUsername());
        user.setCreatedTime(date);
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(date);

        //执行注册业务功能的实现
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {
        return null;
    }

    /**
     * 定义一个md5加密算法
     *
     * @param password
     * @param salt
     * @return
     */
    private String getMd5Password(String password, String salt) {
        for (int i = 0; i < 30; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
