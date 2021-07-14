package com.lqh.practice.sb.disruptor.demo.cc.handler;

import com.lqh.practice.sb.disruptor.demo.cc.CallEvent;
import com.lqh.practice.sb.disruptor.demo.cc.CallEventType;
import com.lqh.practice.sb.disruptor.demo.cc.CallSession;
import com.lqh.practice.sb.disruptor.demo.cc.CallSessionManager;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> 用户评价 CallEventHandler
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/10 18:08
 * @since 2021/6/10 18:08
 */
@Slf4j
public class ScoreHandler9 extends AbstractStateNodeCallEventHandler{
    public ScoreHandler9(CallSessionManager callSessionManager) {
        super(callSessionManager);
    }

    @Override
    protected void handleCallEvent(CallEvent event) {
        CallSession session = callSessionManager.getSession(event.getSessionId());

        if(session == null) {
            log.warn("来电丢失:{}", event);
            return;
        }

        session.put("电话状态", "用户评价");
        session.put("callStatus", getCallState());
        log.info("用户评价 {}", session);
    }

    @Override
    public boolean support(CallEvent event) {
        return event.getEventType() == CallEventType.BYE;
    }

    @Override
    public int getCallState() {
        return StateNode.STATE_SCORE;
    }
}
