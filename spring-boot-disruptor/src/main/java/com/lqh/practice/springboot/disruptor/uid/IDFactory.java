package com.lqh.practice.springboot.disruptor.uid;

import com.lmax.disruptor.EventFactory;
import com.lqh.practice.springboot.disruptor.uid.ID;

/**
 * <p> 类描述: IdSegmentFactory
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/04/15 20:56
 * @since 2021/04/15 20:56
 */
public class IDFactory implements EventFactory<ID> {


    @Override
    public ID newInstance() {
         return new ID();
    }
}
