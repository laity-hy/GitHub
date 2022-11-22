package com.laity.store.mapper;

import com.laity.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("laity004");
        user.setPassword("123456789");
        Integer result = userMapper.insert(user);
        System.out.println(result);
    }

    @Test
    public void findByUsername() {
        User user = new User();
        user.setUsername("laity001");
        User result = userMapper.findByUsername(user.getUsername());
        System.out.println(result.toString());
    }
}
