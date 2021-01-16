package com.lqh.practice.springboot.aop.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p> 类描述: RetryableService
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/16 11:27
 * @since 2021/01/16 11:27
 */
@Slf4j
@Service("RetryableService")
public class RetryableService {
    /**
     * 当前方法一共重试 5 次。 重试条件：服务抛出 AopRuntimeException
     *
     */
    @Retryable(maxAttempts = 5, value = AopRuntimeExption.class)
    public void fiveTimes() {
        log.info("fiveTimes called!");
        throw new AopRuntimeExption();
    }

}
