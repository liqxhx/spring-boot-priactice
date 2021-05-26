package com.lqh.practice.springboot.disruptor;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.RingBuffer;
import com.lqh.practice.springboot.disruptor.uid.ID;

/**
 * <p> 类描述: IdProducer
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/04/15 20:44
 * @since 2021/04/15 20:44
 */
public class IdSegmentProducer {


    private RingBuffer<ID> ringBuffer;

    public IdSegmentProducer(RingBuffer<ID> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

   private EventTranslator idTranslator;
}
