package com.lqh.practice.sb.disruptor.demo.cc.handler;

import com.lqh.practice.sb.disruptor.demo.cc.CallEvent;
import com.lqh.practice.sb.disruptor.demo.cc.CallEventType;
import com.lqh.practice.sb.disruptor.demo.cc.CallSession;
import com.lqh.practice.sb.disruptor.demo.cc.CallSessionManager;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> 系统欢迎 CallEventHandler
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/9 0009 16:06
 * @since 2021/6/9 0009 16:06
 */
@Slf4j
public class WelcomeHandler3 extends AbstractStateNodeCallEventHandler {
    public WelcomeHandler3(CallSessionManager callSessionManager) {
        super(callSessionManager);
    }

    @Override
    protected void handleCallEvent(CallEvent event) {
        CallSession session = callSessionManager.getSession(event.getSessionId());

        if(session != null) {
            session.put("电话状态", "系统欢迎");
            session.put("callStatus", getCallState());
            log.info("播放系统欢迎语音 {}", session);
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
        return StateNode.STATE_WELCOME;
    }
}
