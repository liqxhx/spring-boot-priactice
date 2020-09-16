package com.lqh.priactice.spring.security.service;

import com.lqh.priactice.spring.security.model.User;
import com.lqh.priactice.spring.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p> 类描述: UserService
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/08 20:42
 * @since 2020/07/08 20:42
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> listAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        log.debug("#save#user:{}", user);
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }
}
