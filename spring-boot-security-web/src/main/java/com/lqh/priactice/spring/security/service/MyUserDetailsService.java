package com.lqh.priactice.spring.security.service;

import com.lqh.priactice.spring.security.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p> 类描述: MyUserDetailsService
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/06 10:19
 * @since 2020/09/06 10:19
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录:{}", username);
//        User user = userService.findById(Long.parseLong(username));
//        if(user == null) {
//            throw new UsernameNotFoundException(username);
//        }

        // 用户名、密码、权限集合
        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(
                        username,
                        passwordEncoder.encode("123456"), // 应该在注册时
                        true,
                        true,
                        true,
                        true,
                        AuthorityUtils.commaSeparatedStringToAuthorityList("admin,operator"));

        return userDetails;
    }
}
