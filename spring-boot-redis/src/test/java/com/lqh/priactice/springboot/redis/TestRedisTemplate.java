package com.lqh.priactice.springboot.redis;

import com.lqh.priactice.springboot.redis.module.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p> 类描述: com.lqh.TestRedisTemplate
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/08/20 14:48
 * @since 2020/08/20 14:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisTemplate {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString()  {
//        String value = (String)redisTemplate.opsForValue().get("zmn:cc:infocenter:autocallout:statusinfo");

//        redisTemplate.delete("zmn:cc:infocenter:autocallout:statusinfo");
//        redisTemplate.opsForValue().set("zmn:cc:infocenter:autocallout:statusinfo", "\"{\\\"autoOutCallEnable\\\":true,\\\"maxCalloutAmt\\\":40,\\\"maxTimesPerRecord\\\":1,\\\"timeSpan\\\":{\\\"endTimeStr\\\":\\\"23:59:59\\\",\\\"startTimeStr\\\":\\\"07:00:00\\\",\\\"status\\\":2},\\\"usedCalloutAmt\\\":6}\"");

        System.out.println(redisTemplate.opsForValue().get("zmn:cc:infocenter:autocallout:statusinfo"));
    }

    @Test
    public void testObj(){
        User user=new User("ityouknow@126.com", "smile", "youknow", "know","2020");
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("com.neo", user);
        User u = operations.get("com.neo");
        System.out.println("user: "+u.toString());
    }
}
