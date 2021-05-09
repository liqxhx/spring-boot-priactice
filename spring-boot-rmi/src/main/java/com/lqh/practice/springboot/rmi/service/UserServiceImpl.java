package com.lqh.practice.springboot.rmi.service;

import com.lqh.practice.common.domain.User;

import java.util.Random;

/**
 * <p> 类描述: UserServiceImpl
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/04 15:45
 * @since 2021/05/04 15:45
 */
public class UserServiceImpl implements UserService {
    @Override
    public boolean login(String username, String password) {
        return new Random(System.currentTimeMillis()).nextBoolean();
    }

    @Override
    public User create(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

}
