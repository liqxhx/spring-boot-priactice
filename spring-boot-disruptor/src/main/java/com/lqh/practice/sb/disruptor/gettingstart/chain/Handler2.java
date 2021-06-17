package com.lqh.practice.sb.disruptor.gettingstart.chain;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.lqh.practice.sb.disruptor.gettingstart.LongEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> 类描述: Handler1
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/09 22:42
 * @since 2021/06/09 22:42
 */
@Slf4j
public class Handler2 implements EventHandler<LongEvent>, WorkHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event) throws Exception {
        processLongEvent(event);
    }

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        processLongEvent(event);
    }

    public void processLongEvent(LongEvent event) {
        event.setContent(event.getContent()+"h2");
        log.info("{}", event);
    }


}
