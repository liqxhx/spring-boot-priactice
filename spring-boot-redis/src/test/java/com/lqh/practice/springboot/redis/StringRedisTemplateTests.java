package com.lqh.practice.springboot.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p> 类描述: StringRedisTemplateTests
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/11/26 23:19
 * @since 2020/11/26 23:19
 */@RunWith(SpringRunner.class)
@SpringBootTest
public class StringRedisTemplateTests {
     @Autowired
     private StringRedisTemplate redisTemplate;
     @Test
    public void testZset1() {
         redisTemplate.opsForZSet().add("testz", "a", 100);
         redisTemplate.opsForZSet().add("testz", "b", 200);
     }
}
