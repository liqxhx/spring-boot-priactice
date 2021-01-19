package com.lqh.practice.springboot.redis.lua;

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