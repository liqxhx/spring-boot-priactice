package com.lqh.practice.sb.disruptor.gettingstart;

import com.lmax.disruptor.EventHandler;
import org.springframework.util.StopWatch;

/**
 * <p> 类描述: 创建一个消费者来处理{@link LongEvent}事件
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/05 22:24
 * @since 2021/06/05 22:24
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        StopWatch stopWatch = new StopWatch("LongEventHandler" + event + " " + sequence);
        stopWatch.start();
        Printer.output("consume " + event + " " + System.identityHashCode(event));
        stopWatch.stop();
        Printer.output("consume " + stopWatch.shortSummary());
//        event=null;
//        Printer.output("11 "+event);
    }
}
