package com.lqh.priactice.springboot.springsession.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.text.MessageFormat;

import static com.lqh.priactice.springboot.springsession.pubsub.PubsubConfiguration.CALL_DISPATCH_QUEUE;

/**
 * <p> 类描述: TODO
 *
 * @author qhlee
 * @version TODO
 * @date 2020/06/15 15:02
 * @since 2020/06/15 15:02
 */
@Component
public class RedisPublisher {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostConstruct
    public void init() {
        String hello = "hello";
        new Thread(){
            @Override
           public void run(){
                int i = 0 ;
               while(true) {
                   String msg = hello + i++;
                   redisTemplate.convertAndSend(CALL_DISPATCH_QUEUE, msg);
                   System.out.println(MessageFormat.format("send ---> {0} {1}", CALL_DISPATCH_QUEUE, msg));
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
        }.start();

    }
}
