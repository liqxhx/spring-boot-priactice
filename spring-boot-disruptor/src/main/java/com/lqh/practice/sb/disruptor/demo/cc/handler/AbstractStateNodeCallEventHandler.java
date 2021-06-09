package com.lqh.practice.sb.disruptor.demo.cc.handler;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.lqh.practice.sb.disruptor.demo.cc.CallEvent;
import com.lqh.practice.sb.disruptor.demo.cc.CallSessionManager;

/**
 * <p> 类描述: AbstractStateNode
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/08 22:27
 * @since 2021/06/08 22:27
 */
public abstract class AbstractStateNodeCallEventHandler implements StateNode, CallEventHandler, EventHandler<CallEvent>, WorkHandler<CallEvent> {
    protected final CallSessionManager callSessionManager;

    public AbstractStateNodeCallEventHandler(CallSessionManager callSessionManager) {
        this.callSessionManager = callSessionManager;
    }

    @Override
    public void onEvent(CallEvent event, long sequence, boolean endOfBatch) throws Exception {
        handle(event);
    }

    @Override
    public void onEvent(CallEvent event) throws Exception {
        handle(event);
    }

    @Override
    public void handle(CallEvent event) {
        if(!support(event)) {return;}

        handleCallEvent(event);
    }

    protected abstract void handleCallEvent(CallEvent event);
}
