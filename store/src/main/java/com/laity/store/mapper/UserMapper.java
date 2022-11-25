package com.laity.store.mapper;

import com.laity.store.entity.User;

/**
 * 用户模块的持久层接口
 */
public interface UserMapper {
    /**
     * 插入用户的数据
     *
     * @param user 用户的数据
     * @return 受影响的行数（在增、删、改，都受影响的行数作为返回值）
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户的数据
     *
     * @param username 用户名
     * @return 返回查询结果User
     */
    User findByUsername(String username);
}
