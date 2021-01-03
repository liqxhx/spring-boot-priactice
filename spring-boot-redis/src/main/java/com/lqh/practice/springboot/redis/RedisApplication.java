package com.lqh.practice.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * <p> 类描述: RedisApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/08/20 15:53
 * @since 2020/08/20 15:53
 */
@SpringBootApplication
@ComponentScan("com.lqh.practice.springboot.redis")
public class RedisApplication implements ApplicationRunner {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    /**
    * main
    */
    public static void main(String[] args){
        SpringApplication.run(RedisApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        int i =0;
        while(i < 10) {
            TimeUnit.SECONDS.sleep(1);
            stringRedisTemplate.convertAndSend("notice", "ping"+i);
            System.out.println("send: ping"+i);
            i++;
        }
        System.out.println("over");

//        System.in.read();
    }
}
