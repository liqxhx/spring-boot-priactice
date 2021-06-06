package com.lqh.practice.sb.disruptor.chain;

import com.lmax.disruptor.ExceptionHandler;
import com.lqh.practice.sb.disruptor.gettingstart.LongEvent;
import com.lqh.practice.sb.disruptor.gettingstart.Printer;


/**
 * <p> 类描述: LongEventExceptionHandler
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/06 10:36
 * @since 2021/06/06 10:36
 */
public class LongEventExceptionHandler implements ExceptionHandler<LongEvent> {
    @Override
    public void handleEventException(Throwable ex, long sequence, LongEvent event) {

    }

    @Override
    public void handleOnStartException(Throwable ex) {
        Printer.output("LongEventExceptionHandler#handleOnStartException:" + ex);
    }

    @Override
    public void handleOnShutdownException(Throwable ex) {

    }
}
