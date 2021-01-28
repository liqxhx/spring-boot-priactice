package com.lqh.practice.spring.boot.starter.redisson;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p> 类描述: RedissonProperties
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/28 22:23
 * @since 2021/01/28 22:23
 */
@ConfigurationProperties(prefix = "my.redisson")
@Data
public class RedissonProperties {
    private String host;
    private int port;
    private int timeout;
    private boolean ssl;
}
