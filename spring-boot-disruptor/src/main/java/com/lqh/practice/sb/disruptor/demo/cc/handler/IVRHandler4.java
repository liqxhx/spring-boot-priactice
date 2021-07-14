package com.lqh.practice.sb.disruptor.demo.cc.handler;

import com.lqh.practice.sb.disruptor.demo.cc.CallEvent;
import com.lqh.practice.sb.disruptor.demo.cc.CallEventType;
import com.lqh.practice.sb.disruptor.demo.cc.CallSession;
import com.lqh.practice.sb.disruptor.demo.cc.CallSessionManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * <p> IVR CallEventHandler
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/10 0010 17:12
 * @since 2021/6/10 0010 17:12
 */
@Slf4j
public class IVRHandler4 extends AbstractStateNodeCallEventHandler{
    public IVRHandler4(CallSessionManager callSessionManager) {
        super(callSessionManager);
    }

    @Override
    protected void handleCallEvent(CallEvent event) {
        CallSession session = callSessionManager.getSession(event.getSessionId());

        if(session != null) {

            if(new Random().nextBoolean()) {
                session.put("电话状态", "IVR");
                session.put("callStatus", getCallState());
                log.info("播IVR {}", session);
            } else {
                log.info("不播IVR {}",  session);
            }

        } else {
            log.warn("来电丢失:{}", event);
        }
    }

    @Override
    public boolean support(CallEvent event) {
        return event.getEventType() == CallEventType.INCOMING;
    }

    @Override
    public int getCallState() {
        return StateNode.STATE_IVR;
    }
}
