package com.lqh.priactice.springboot.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

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
public class TestRedisTemplate2 {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * zrangebyscore
     * zremrangebyscore
     *
     * eval "local rt=redis.call('zrangebyscore', KEYS[1], 0, 100) if rt then redis.call('ZREMRANGEBYSCORE', KEYS[1], 0, 100) return rt end return null" 1 "testz"
     */
    @Test
    public void testLuaZsetOpt() {
        String LUA = "local rt=redis.call('ZRANGEBYSCORE', KEYS[1], ARGV[1], ARGV[2]) \n" +
                "if rt then \n"+
                "redis.call('ZREMRANGEBYSCORE', KEYS[1], ARGV[1], ARGV[2]) \n" +
                "return rt\n"+
                "end\n"+
                "return null";


        DefaultRedisScript<List> rs = new DefaultRedisScript<>();
        //设置脚本
        rs.setScriptText(LUA);
        //定义返回类型。注意如果没有这个定义，spring不会返回结果
        rs.setResultType(List.class);

        List obj = redisTemplate.execute(rs, Arrays.asList("testz"), "0", "100");
        System.out.println(obj);

    }

    @Test
    public void testLua() {
        DefaultRedisScript<List> rs = new DefaultRedisScript<List>();
        rs.setResultType(List.class);
        rs.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/lua1.lua")));

        System.out.println(rs.getScriptAsString());
        Object obj = redisTemplate.execute(rs, Arrays.asList("testz"), "0", "200");
        System.out.println(obj);
    }
}
