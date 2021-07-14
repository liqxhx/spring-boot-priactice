package com.lqh.practice.sb.disruptor.demo.cc.handler;

import com.lmax.disruptor.ExceptionHandler;
import com.lqh.practice.sb.disruptor.demo.cc.CallEvent;
import lombok.extern.slf4j.Slf4j;


/**
 * <p> 类描述: CallEventExceptionHandler
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/06 10:36
 * @since 2021/06/06 10:36
 */
@Slf4j
public class CallEventExceptionHandler implements ExceptionHandler<CallEvent> {
    @Override
    public void handleEventException(Throwable ex, long sequence, CallEvent event) {
        log.error("seq:{}, event:{}, message:{}", sequence, event, ex.getMessage(), ex);
    }

    @Override
    public void handleOnStartException(Throwable ex) {
        log.error(ex.getMessage(), ex);
    }

    @Override
    public void handleOnShutdownException(Throwable ex) {
        log.error(ex.getMessage(), ex);
    }
}
