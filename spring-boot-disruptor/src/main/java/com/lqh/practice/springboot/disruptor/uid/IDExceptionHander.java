package com.lqh.practice.springboot.disruptor.uid;

import com.lmax.disruptor.ExceptionHandler;

/**
 * <p> 类描述: IdExceptionHander
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/04/15 21:29
 * @since 2021/04/15 21:29
 */
public class IDExceptionHander implements ExceptionHandler {
    @Override
    public void handleEventException(Throwable ex, long sequence, Object event) {

    }

    @Override
    public void handleOnStartException(Throwable ex) {

    }

    @Override
    public void handleOnShutdownException(Throwable ex) {

    }
}
