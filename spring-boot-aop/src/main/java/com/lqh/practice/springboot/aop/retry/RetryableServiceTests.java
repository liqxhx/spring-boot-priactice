package com.lqh.practice.springboot.aop.retry;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * <p> 类描述: RetryableServiceTests
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/16 11:30
 * @since 2021/01/16 11:30
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RetryableServiceTests {
    @Resource(name="RetryableService")
    RetryableService retryableService;

    @Test
    public void testRetry() {
        retryableService.fiveTimes();;
    }
}
