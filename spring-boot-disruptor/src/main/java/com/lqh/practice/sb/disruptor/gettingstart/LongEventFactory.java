package com.lqh.practice.sb.disruptor.gettingstart;

import com.lmax.disruptor.EventFactory;

/**
 * <p> 类描述: EventFactory 来执行构造 {@link LongEvent}
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/05 22:22
 * @since 2021/06/05 22:22
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }

}
