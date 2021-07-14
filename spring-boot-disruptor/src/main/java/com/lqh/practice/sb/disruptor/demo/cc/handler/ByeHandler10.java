package com.lqh.practice.sb.disruptor.demo.cc.handler;

import com.lqh.practice.sb.disruptor.demo.cc.CallEvent;
import com.lqh.practice.sb.disruptor.demo.cc.CallEventType;
import com.lqh.practice.sb.disruptor.demo.cc.CallSession;
import com.lqh.practice.sb.disruptor.demo.cc.CallSessionManager;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> Bye CallEventHandler
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/10 18:08
 * @since 2021/6/10 18:08
 */
@Slf4j
public class ByeHandler10 extends AbstractStateNodeCallEventHandler{
    public ByeHandler10(CallSessionManager callSessionManager) {
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
            session.put("电话状态", "挂断");
            session.put("通话结束时间", System.currentTimeMillis());
            session.put("callStatus", getCallState());
            log.info("电话挂断 {}", session);
            log.info("生成通话记录");
        }
    }

    @Override
    public boolean support(CallEvent event) {
        return event.getEventType() == CallEventType.BYE;
    }

    @Override
    public int getCallState() {
        return StateNode.STATE_BYE;
    }
}
