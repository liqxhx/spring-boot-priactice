package com.lqh.practice.sb.disruptor.gettingstart;

import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

/**
 * <p> 类描述: 使用旧 API 发布 {@link LongEvent}
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/05 22:30
 * @since 2021/06/05 22:30
 */
@Slf4j
public class LongEventProducer2 {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer2(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(long data) {
        long sequence = ringBuffer.next();  // Grab the next sequence
        try {
            StopWatch stopWatch = new StopWatch("produce");
            stopWatch.start();

            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
            // for the sequence
            event.set(data);  // Fill with data

            stopWatch.stop();
            log.debug("produce {} {}", data, stopWatch.shortSummary());
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
