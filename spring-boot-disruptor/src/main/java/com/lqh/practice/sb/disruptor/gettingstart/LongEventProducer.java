package com.lqh.practice.sb.disruptor.gettingstart;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * <p> 类描述: 使用旧 API 发布 {@link LongEvent}
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/05 22:30
 * @since 2021/06/05 22:30
 */
public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb) {
        long sequence = ringBuffer.next();  // Grab the next sequence
        try {
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
            // for the sequence
            event.set(bb.getLong(0));  // Fill with data
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
