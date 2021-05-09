package com.lqh.practice.springboot.rmi.service;

import com.lqh.practice.common.domain.User;

/**
 * <p> 类描述: UserService
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/04 15:42
 * @since 2021/05/04 15:42
 */
public interface UserService {
    boolean login(String username, String password);
    User create(String username, String password);
}
