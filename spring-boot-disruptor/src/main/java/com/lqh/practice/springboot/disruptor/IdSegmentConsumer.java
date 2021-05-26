package com.lqh.practice.springboot.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.lqh.practice.springboot.disruptor.uid.ID;

import java.util.function.Supplier;

/**
 * <p> 类描述: IdSegmentConsumer
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/04/15 20:53
 * @since 2021/04/15 20:53
 */
public class IdSegmentConsumer implements WorkHandler<ID>, EventHandler<ID>, Supplier<Long> {
    private String id;
    public IdSegmentConsumer(int id) {
        this.id = "aaabbbccc"+id;
    }

    private volatile Long value;

    @Override
    public void onEvent(ID event) throws Exception {
        this.value = event.getValue();
    }

    @Override
    public void onEvent(ID event, long sequence, boolean endOfBatch) throws Exception {
        onEvent(event);
    }

    @Override
    public Long get() {
        return this.value;
    }
}
