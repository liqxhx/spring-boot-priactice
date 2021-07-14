package com.lqh.practice.sb.disruptor.gettingstart;

import com.lmax.disruptor.ExceptionHandler;

/**
 * <p> 类描述: LongEventExceptionHandler
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/09 21:22
 * @since 2021/06/09 21:22
 */
public class LongEventExceptionHandler implements ExceptionHandler<LongEvent> {
    @Override
    public void handleEventException(Throwable ex, long sequence, LongEvent event) {

    }

    @Override
    public void handleOnStartException(Throwable ex) {

    }

    @Override
    public void handleOnShutdownException(Throwable ex) {

    }
}
