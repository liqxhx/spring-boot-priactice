package com.lqh.priactice.springboot.springsession.pubsub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * <p> 类描述: TODO
 *
 * @author qhlee
 * @version TODO
 * @date 2020/06/15 15:00
 * @since 2020/06/15 15:00
 */
@Configuration
public class PubsubConfiguration {
    public static final String CALL_DISPATCH_QUEUE = "call_dispatch_queue";

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic(CALL_DISPATCH_QUEUE));
//        container.addMessageListener(listenerAdapter, new PatternTopic("testkafka1"));//配置要订阅的订阅项
        return container;
    }
}
