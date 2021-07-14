package com.lqh.practice.sb.disruptor.gettingstart;

import com.lmax.disruptor.*;

import java.nio.ByteBuffer;

/**
 * <p> 类描述: 使用Translators发布事件{@link LongEvent}
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/05 22:31
 * @since 2021/06/05 22:31
 */
public class LongEventProducerWithTranslator {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * @see EventTranslator
     * @see EventTranslatorOneArg
     * @see EventTranslatorTwoArg
     * @see EventTranslatorThreeArg
     * @see EventTranslatorVararg
     */
    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR =
            new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
                @Override
                public void translateTo(LongEvent event, long sequence, ByteBuffer bb) {
                    event.set(bb.getLong(0));
                }
            };

    public void onData(ByteBuffer bb) {
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}
