package com.lqh.practice.redis.redisson;

import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * <p> 类描述: RedissonApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/29 18:58
 * @since 2021/01/29 18:58
 */
@SpringBootApplication
public class RedissonApplication implements ApplicationRunner {
    @Autowired
    private RedissonClient redissonClient;

    /**
    * main
    */
    public static void main(String[] args){
        new SpringApplicationBuilder().web(WebApplicationType.NONE).sources(RedissonApplication.class).run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RSet<String> set = redissonClient.getSet("mySet");
        set.add("1");
        set.add("2");
        set.add("3");

        set.contains("1");


        RMap<String, String> rmap = redissonClient.getMap("myMap");
        rmap.put("key1", "val1");
        rmap.put("key2", "val2");


        RBucket<String> b = redissonClient.getBucket("xx");
        if (b.get() == null) {
            b.set("a");
        }
        System.out.println(b.get());

    }
}
