package com.lqh.priactice.springboot.springsession.config;

import org.springframework.context.annotation.Configuration;

/**
 * <p> 类描述: TODO
 *
 * @author qhlee
 * @version TODO
 * @date 2020/06/15 14:28
 * @since 2020/06/15 14:28
 */
@Configuration
public class KeyExpirationEventMessageListenerConfiguration {
//    @Bean
//    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
////            container.addMessageListener(new RedisExpiredListener(), new PatternTopic("__keyevent@0__:expired"));
//        return container;
//    }

//    @Bean
//    public KeyExpirationEventMessageListener keyExpirationEventMessageListener(RedisConnectionFactory redisConnectionFactory) {
//        return new KeyExpirationEventMessageListener(container(redisConnectionFactory)) {
//            @Override
//            public void onMessage(Message message, byte[] pattern) {
//                System.out.println("KeyExpirationEventMessageListener ".concat(message.toString()));
//            }
//        };
//    }


}
