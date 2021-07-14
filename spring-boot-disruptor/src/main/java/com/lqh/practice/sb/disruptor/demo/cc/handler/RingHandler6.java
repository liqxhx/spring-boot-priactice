package com.lqh.practice.sb.disruptor.demo.cc.handler;

import com.lqh.practice.sb.disruptor.demo.cc.CallEvent;
import com.lqh.practice.sb.disruptor.demo.cc.CallEventType;
import com.lqh.practice.sb.disruptor.demo.cc.CallSession;
import com.lqh.practice.sb.disruptor.demo.cc.CallSessionManager;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> 座席响铃 CallEventHandler
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/10 0010 17:53
 * @since 2021/6/10 0010 17:53
 */
@Slf4j
public class RingHandler6 extends AbstractStateNodeCallEventHandler {
    public RingHandler6(CallSessionManager callSessionManager) {
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
            session.put("座席响铃开始时间", event.getEventTime());
            session.put("电话状态", "座席响铃");
            session.put("callStatus", getCallState());
            log.info("座席响应 & 弹屏 {}", session);
        }
    }

    @Override
    public boolean support(CallEvent event) {
        return event.getEventType() == CallEventType.RING;
    }

    @Override
    public int getCallState() {
        return StateNode.STATE_AGENT_RING;
    }
}
