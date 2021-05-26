package com.lqh.practice.springboot.disruptor.uid;

import com.lmax.disruptor.EventTranslator;

/**
 * <p> 类描述: IDBufferManager
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/04/18 09:26
 * @since 2021/04/18 09:26
 */
public class IDTranslator implements EventTranslator<ID> {
    private IDSegmentService segmentService;

    @Override
    public void translateTo(ID event, long sequence) {
        event.setValue(segmentService.next());
    }
}
