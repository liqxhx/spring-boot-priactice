package com.lqh.practice.actuator.actuate;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.redis.RedisHealthIndicator;

/**
 * <p> 类描述: CustomerHealthIndicator
 * @see RedisHealthIndicator
 * @author qhlee
 * @version 1.0
 * @date 2021/01/28 23:30
 * @since 2021/01/28 23:30
 */

public class CustomerHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {

    }
}
