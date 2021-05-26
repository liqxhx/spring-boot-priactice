package com.lqh.practice.springboot.disruptor.uid;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p> 类描述: IDSegment
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/04/18 09:28
 * @since 2021/04/18 09:28
 */
public class IDSegment {
    private AtomicLong max;
    private AtomicLong min;

    public long next() {
        if(min.get() <= max.get()) {
            return min.getAndIncrement();
        }
    }
}
