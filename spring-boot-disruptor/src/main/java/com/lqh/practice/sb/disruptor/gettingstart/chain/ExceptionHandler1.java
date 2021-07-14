package com.lqh.practice.sb.disruptor.gettingstart.chain;

import com.lmax.disruptor.ExceptionHandler;
import com.lqh.practice.sb.disruptor.gettingstart.LongEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> 类描述: ExceptionHandler1
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/09 22:49
 * @since 2021/06/09 22:49
 */
@Slf4j
public class ExceptionHandler1 implements ExceptionHandler<LongEvent> {
    @Override
    public void handleEventException(Throwable ex, long sequence, LongEvent event) {
        log.error("seq:{}, event:{}, ex:{}", sequence, event, ex.getMessage(), ex);
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
