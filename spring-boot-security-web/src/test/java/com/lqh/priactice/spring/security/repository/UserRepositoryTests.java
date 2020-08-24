package com.lqh.priactice.spring.security.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p> 类描述: UserRepositoryTests
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/08/24 16:06
 * @since 2020/08/24 16:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() {

    }

}
