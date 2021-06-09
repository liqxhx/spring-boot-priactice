package com.lqh.practice.sb.disruptor.gettingstart;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

/**
 * <p> 类描述: 创建一个消费者来处理{@link LongEvent}事件
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/05 22:24
 * @since 2021/06/05 22:24
 */
@Slf4j
public class LongEventHandler implements EventHandler<LongEvent> , WorkHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        processEvent(event);
    }

    @Override
    public void onEvent(LongEvent event) throws Exception {
        processEvent(event);
    }

    private void processEvent(LongEvent event) {
        StopWatch stopWatch = new StopWatch("LongEventHandler");
        stopWatch.start();

        // consume event

        stopWatch.stop();
        log.debug("consume {} {}", event.get(),  stopWatch.shortSummary());
    }
}
