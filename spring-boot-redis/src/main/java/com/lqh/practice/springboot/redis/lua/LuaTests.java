package com.lqh.practice.springboot.redis.lua;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LuaTests {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testSetIfAbsentThenExpire() throws Exception {
        File file = ResourceUtils.getFile("classpath:lua/setIfAbsentThenExpire.lua");

        String luaStr = FileUtils.readFileToString(file, "utf-8");

        Assert.assertNotNull(luaStr);

        System.out.println(luaStr);

        DefaultRedisScript<Boolean> script = new DefaultRedisScript();
        script.setScriptText(luaStr);
        script.setResultType(Boolean.class);

        System.out.println(redisTemplate.execute(script, Collections.singletonList("key111"), "13883207608", "60"));
    }
}