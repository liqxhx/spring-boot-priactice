package com.lqh.practice.sb.disruptor.demo.dal;

import com.lmax.disruptor.ExceptionHandler;
import com.lqh.practice.sb.disruptor.gettingstart.Printer;

/**
 * <p> Persistence ExceptionHandler
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/9 14:43
 * @since 2021/6/9 14:43
 */
public class PersistenceExceptionHandler implements ExceptionHandler<PersistenceEvent> {
    @Override
    public void handleEventException(Throwable ex, long sequence, PersistenceEvent event) {
        Printer.output("handleEventException: " + event+", @seq: " + sequence +", ex:" + ex);
    }

    @Override
    public void handleOnStartException(Throwable ex) {
        Printer.output("handleOnStartException, ex:" + ex.getMessage());
    }

    @Override
    public void handleOnShutdownException(Throwable ex) {
        Printer.output("handleOnShutdownException, ex:" + ex.getMessage());
    }
}
