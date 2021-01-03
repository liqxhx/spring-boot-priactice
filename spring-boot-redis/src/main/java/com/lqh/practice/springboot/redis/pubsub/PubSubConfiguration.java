package com.lqh.practice.springboot.redis.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * <p> 类描述: RedisConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/08/20 14:38
 * @since 2020/08/20 14:38
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class PubSubConfiguration{
    public static final String PUB_SUB_TOPIC_NAME_PTN = "notice";

    //利用反射来创建监听到消息之后的执行方法   ，RedisReceiver 是接受者的类，receiveMessage接受者的方法
    @Bean
    public MessageListenerAdapter notificationListenerAdapter(@Autowired NotificationManager listener) {
        return new MessageListenerAdapter(listener, "onNotification");
    }

    @Bean
    public RedisMessageListenerContainer container(@Autowired RedisConnectionFactory redisConnectionFactory, @Autowired NotificationManager notificationManager) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(notificationListenerAdapter(notificationManager), new PatternTopic(PUB_SUB_TOPIC_NAME_PTN));
        return container;
    }
}
