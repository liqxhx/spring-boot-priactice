package com.lqh.practice.sb.disruptor.demo.cc.handler;

import com.lqh.practice.sb.disruptor.demo.cc.CallEvent;
import com.lqh.practice.sb.disruptor.demo.cc.CallEventType;
import com.lqh.practice.sb.disruptor.demo.cc.CallSession;
import com.lqh.practice.sb.disruptor.demo.cc.CallSessionManager;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> 通话中 CallEventHandler
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/10 0010 18:08
 * @since 2021/6/10 0010 18:08
 */
@Slf4j
public class AnsweredHandler8 extends AbstractStateNodeCallEventHandler{
    public AnsweredHandler8(CallSessionManager callSessionManager) {
        super(callSessionManager);
    }

    @Override
    protected void handleCallEvent(CallEvent event) {
        CallSession session = callSessionManager.getSession(event.getSessionId());

        if(session == null) {
            log.warn("来电丢失:{}", event);
            return;
        }

        if(Boolean.valueOf(String.valueOf(session.get("分配成功标记")))) {
            session.put("电话状态", "通话中");
            session.put("callStatus", getCallState());
            log.info("通话建立 {}", session);
        }
    }

    @Override
    public boolean support(CallEvent event) {
        return event.getEventType() == CallEventType.ANSWERED;
    }

    @Override
    public int getCallState() {
        return StateNode.STATE_ANSWERED;
    }
}
