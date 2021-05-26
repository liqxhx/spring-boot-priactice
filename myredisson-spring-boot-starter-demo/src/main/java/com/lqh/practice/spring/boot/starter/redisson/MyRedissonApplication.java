package com.lqh.practice.spring.boot.starter.redisson;

import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * <p> 类描述: MyRedissonApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/28 22:47
 * @since 2021/01/28 22:47
 */
@SpringBootApplication
public class MyRedissonApplication implements ApplicationRunner {
    @Autowired
    RedissonClient redissonClient;

    /**
    * main
    */
    public static void main(String[] args){
        new SpringApplicationBuilder().sources(MyRedissonApplication.class).web(WebApplicationType.NONE).run(args);
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.err.println(redissonClient);

        RBucket bucket = redissonClient.getBucket("name");
        if(bucket.get() == null) {
            bucket.set("hello");
        }

        RMap r = redissonClient.getMap("a:b:c", new StringCodec());
        r.put("k", "v");

        System.err.println(bucket.get().toString());
    }
}
