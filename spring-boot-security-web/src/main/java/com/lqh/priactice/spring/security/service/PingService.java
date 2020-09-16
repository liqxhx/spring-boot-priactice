package com.lqh.priactice.spring.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p> 类描述: AsyncService
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/10 17:06
 * @since 2020/09/10 17:06
 */
@Component
@Slf4j
public class PingService {
    @Async
    public void ping() {
        log.debug("#service#ping");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("#service#pong");
    }

    @Async
    public long pingWithResult() {
        long b = System.currentTimeMillis();
        log.debug("#service#pingWithResult");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("#service#pingWithResult");
        return System.currentTimeMillis() - b;
    }
}
