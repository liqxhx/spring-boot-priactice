package com.lqh.practice.spring.boot.starter.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> 类描述: RedissonAutoConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/28 21:42
 * @since 2021/01/28 21:42
 */
@ConditionalOnClass(Redisson.class)
@Configuration
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonAutoConfiguration {
    @Bean
    public RedissonClient redissonClient(RedissonProperties properties) {
        Config config = new Config();
        String prefix = "redis://";

        if(properties.isSsl()) {
            prefix = "rediss://";
        }

        config.useSingleServer()
                .setAddress(prefix + properties.getHost() + ":" + properties.getPort())
                .setConnectTimeout(properties.getTimeout());


        return Redisson.create(config);
    }
}
