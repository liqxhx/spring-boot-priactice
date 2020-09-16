package com.lqh.priactice.springboot.redis.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p> 类描述: CustomeIndicator
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/08/28 14:34
 * @since 2020/08/28 14:34
 */
@Endpoint(id="custome")
@Component
public class CustomeIndicator {
    private String status;
    @ReadOperation
    public String getStatus() {
        return this.status;
    }

    @WriteOperation
    public String setStatus(String status) {
        this.status = status;
        return this.status;
    }
}
