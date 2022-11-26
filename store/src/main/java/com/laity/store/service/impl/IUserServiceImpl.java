package com.laity.store.service.impl;

import com.laity.store.entity.User;
import com.laity.store.mapper.UserMapper;
import com.laity.store.service.IUserService;
import com.laity.store.service.ex.InsertException;
import com.laity.store.service.ex.PasswordNotMatchException;
import com.laity.store.service.ex.UserNotFoundException;
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
        String username = user.getUsername();//通过user参数来获取传递过来的username

        User result = userMapper.findByUsername(username);//调用findByUsername(String username)判断用户是否被注册过

        if (result != null) {//判断结果集是否为null则抛出用户名被占用的异常
            throw new UsernameDuplicatedException("用户名已被占用异常");//抛出异常
        }

        String oldPassword = user.getPassword();//密码加密处理的实现：md5算法的实现
        String salt = UUID.randomUUID().toString().toUpperCase();//获取盐值
        String newPassword = getMd5Password(oldPassword, salt);//整体加密处理

        user.setPassword(newPassword);
        user.setSalt(salt);
        user.setIsDelete(0);//补全数据：is_delete设置为0，4个日志数据
        Date date = new Date();
        user.setCreatedUser(user.getUsername());
        user.setCreatedTime(date);
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(date);

        Integer rows = userMapper.insert(user);//执行注册业务功能的实现
        if (rows != 1) {
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);

        if (result == null) {
            throw new UserNotFoundException("用户数据不存在异常");
        }

        String oldPassword = result.getPassword();
        String salt = result.getSalt();
        String newMd5Password = getMd5Password(password, salt);
        if (!oldPassword.equals(newMd5Password)) {
            throw new PasswordNotMatchException("用户密码错误异常");
        }

        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在异常");
        }

        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        return user;
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
