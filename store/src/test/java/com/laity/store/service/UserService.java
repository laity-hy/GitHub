package com.laity.store.service;

import com.laity.store.entity.User;
import com.laity.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserService {
    @Autowired
    private IUserService userService;

    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("laity008");
            user.setPassword("123456789");
            userService.reg(user);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login() {
        User result = userService.login("laity0016", "123456789");
        System.out.println(result.toString());
    }
}
