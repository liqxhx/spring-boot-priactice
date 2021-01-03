package com.lqh.practice.springboot.redis.pubsub;

import com.lqh.practice.common.domain.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * <p> 类描述: PubsubController
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/03 19:12
 * @since 2021/01/03 19:12
 */
@RestController
@RequestMapping("/pub")
public class PubsubController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostConstruct
    public void init() {
        System.out.println("init");
    }
    /**
     * redis生产者测试
     * @param data
     * @return
     */
    @RequestMapping("/send/{data}")
    @ResponseBody
    public ResponseDTO send(@PathVariable(name = "data") String data) {
        redisTemplate.convertAndSend(PubSubConfiguration.PUB_SUB_TOPIC_NAME_PTN, data);
        return ResponseDTO.success("success");
    }
}
