package com.lqh.practice.springboot.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
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
public class TestRedisTemplate {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testPip1() {
        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                connection.zRemRangeByScore("abc".getBytes(), 0, 3);
                Long index = connection.zRank("abc".getBytes(), "k3".getBytes());
                System.out.println(index);
                return null;
            }
        });
    }

    @Test
    public void testLuaAdd() {
        String UA_REMOVE_AND_CACHE_CALL_OUT = "redis.call('ZREMRANGEBYSCORE', KEYS[1], 0, ARGV[2]- ARGV[1])\n" +
                "redis.call('ZADD', KEYS[1], ARGV[2], ARGV[3])\n" +
                "return true";

        long expireMils= 2592000000L;

        DefaultRedisScript<Boolean> rs = new DefaultRedisScript<>();
        //设置脚本
        rs.setScriptText(UA_REMOVE_AND_CACHE_CALL_OUT);
        //定义返回类型。注意如果没有这个定义，spring不会返回结果
        rs.setResultType(Boolean.class);

        for (int i = 0; i < 8; i++) {
            redisTemplate.execute(rs, Arrays.asList("abc"), expireMils, System.currentTimeMillis(), "k"+i);
        }
        Object succ = redisTemplate.execute(rs, Arrays.asList("abc"), expireMils, System.currentTimeMillis(), "k9");
        System.out.println(succ);
    }

    @Test
    public void testLua1() {
        String LUA_CHECK_EXIST_CALL_OUT = "redis.call('ZREMRANGEBYSCORE', KEYS[1], 0, ARGV[2]- ARGV[1])\n" +
//                "local e = redis.call('ZRANK', KEYS[1], ARGV[3])\n" +
//                "echo e"+
                "if redis.call('ZRANK', KEYS[1], ARGV[3]) then\n" +
                "return true\n" +
                "end\n" +
                "return false";

        long expireMils= 25L;

        DefaultRedisScript<Boolean> rs = new DefaultRedisScript<>();
        //设置脚本
        rs.setScriptText(LUA_CHECK_EXIST_CALL_OUT);
        //定义返回类型。注意如果没有这个定义，spring不会返回结果
        rs.setResultType(Boolean.class);

        Object ret = redisTemplate.execute(rs, Arrays.asList("abc"), expireMils, System.currentTimeMillis(), "k0");
        System.out.println(ret);
    }


    @Test
    public void testString()  {
//        String value = (String)redisTemplate.opsForValue().get("zmn:cc:infocenter:autocallout:statusinfo");

//        redisTemplate.delete("zmn:cc:infocenter:autocallout:statusinfo");
//        redisTemplate.opsForValue().set("zmn:cc:infocenter:autocallout:statusinfo", "\"{\\\"autoOutCallEnable\\\":true,\\\"maxCalloutAmt\\\":40,\\\"maxTimesPerRecord\\\":1,\\\"timeSpan\\\":{\\\"endTimeStr\\\":\\\"23:59:59\\\",\\\"startTimeStr\\\":\\\"07:00:00\\\",\\\"status\\\":2},\\\"usedCalloutAmt\\\":6}\"");

        System.out.println(redisTemplate.opsForValue().get("zmn:cc:infocenter:autocallout:statusinfo"));
    }

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


        DefaultRedisScript<Object> rs = new DefaultRedisScript<>();
        //设置脚本
        rs.setScriptText(LUA);
        //定义返回类型。注意如果没有这个定义，spring不会返回结果
        rs.setResultType(Object.class);

        Object obj = redisTemplate.execute(rs, Arrays.asList("testz"), 0, 100);
        System.out.println(obj);

    }

    @Test
    public void testLua() {
        DefaultRedisScript<List> rs = new DefaultRedisScript<List>();
        rs.setResultType(List.class);
        rs.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/lua1.lua")));

        System.out.println(rs.getScriptAsString());
        Object obj = redisTemplate.execute(rs, Arrays.asList("testz"), 0, 100);
        System.out.println(obj);
    }
}
