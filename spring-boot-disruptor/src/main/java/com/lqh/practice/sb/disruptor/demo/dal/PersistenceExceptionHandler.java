package com.lqh.practice.sb.disruptor.demo.dal;

import com.lmax.disruptor.ExceptionHandler;
import com.lqh.practice.sb.disruptor.gettingstart.Printer;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> Persistence ExceptionHandler
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/9 14:43
 * @since 2021/6/9 14:43
 */
@Slf4j
public class PersistenceExceptionHandler implements ExceptionHandler<PersistenceEvent> {
    @Override
    public void handleEventException(Throwable ex, long sequence, PersistenceEvent event) {
        log.error("handleEventException, {} seq:{} ex:{}", event, sequence, ex.getMessage(), ex);
    }

    @Override
    public void handleOnStartException(Throwable ex) {
        log.error("handleOnStartException, ex:{}", ex.getMessage(), ex);
    }

    @Override
    public void handleOnShutdownException(Throwable ex) {
        log.error("handleOnShutdownException, ex:{}", ex.getMessage(), ex);
    }
}
