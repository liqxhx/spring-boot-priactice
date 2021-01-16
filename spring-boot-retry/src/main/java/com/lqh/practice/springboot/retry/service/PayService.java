package com.lqh.practice.springboot.retry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * <p> 类描述: PayService
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/16 11:48
 * @since 2021/01/16 11:48
 */
@Service
@Slf4j
public class PayService {

    private final int totalNum = 100000;

    /**
     * 重试三次抛出异常
     * value值表示当哪些异常的时候触发重试，
     * maxAttempts表示最大重试次数默认为3，
     * delay表示重试的延迟时间，
     * multiplier表示上一次延时时间是这一次的倍数
     *
     * @param num
     * @return
     * @throws Exception
     */
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public int minGoodsnum(int num) throws Exception{
        log.info("minGoodsnum开始"+ LocalTime.now());
        if(num <= 0){
            throw new Exception("数量不对");
        }
        log.info("minGoodsnum执行结束");
        return totalNum - num;
    }

    /**
     * 使用@Recover注解，当重试次数达到设置的次数的时候，还是失败抛出异常，执行的回调函数。
     * 和minGoodsnum定义在一个类中
     * @param e
     * @return
     */
    @Recover
    public int recover(Exception e){
        log.warn("减库存失败！！！");
        throw new RuntimeException(e);
        //记日志到数据库
//        return totalNum;
    }
}