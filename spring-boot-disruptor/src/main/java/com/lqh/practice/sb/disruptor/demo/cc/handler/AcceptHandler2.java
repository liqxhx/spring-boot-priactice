package com.lqh.practice.sb.disruptor.demo.cc.handler;

import com.lqh.practice.sb.disruptor.demo.cc.CallEvent;
import com.lqh.practice.sb.disruptor.demo.cc.CallEventType;
import com.lqh.practice.sb.disruptor.demo.cc.CallSession;
import com.lqh.practice.sb.disruptor.demo.cc.CallSessionManager;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> 类描述: 系统受理 CallEventHandler
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/08 22:54
 * @since 2021/06/08 22:54
 */
@Slf4j
public class AcceptHandler2 extends AbstractStateNodeCallEventHandler {

    public AcceptHandler2(CallSessionManager callSessionManager) {
        super(callSessionManager);
    }

    @Override
    public int getCallState() {
        return StateNode.STATE_ACCEPTED;
    }

    @Override
    public boolean support(CallEvent event) {
        return CallEventType.INVITE == event.getEventType();
    }

    @Override
    public void handleCallEvent(CallEvent event) {
        CallSession session = callSessionManager.getSession(event.getSessionId());
        if (session != null) {
            session.put("电话状态", "系统受理");
            session.put("callStatus", getCallState());
            log.info("系统受理 {}", session);
        } else {
            log.warn("未找到会话：未通过黑名单校验或来电丢失", event);
        }
    }
}
