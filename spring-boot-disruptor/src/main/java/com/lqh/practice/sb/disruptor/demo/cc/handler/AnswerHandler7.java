package com.lqh.practice.sb.disruptor.demo.cc.handler;

import com.lqh.practice.sb.disruptor.demo.cc.CallEvent;
import com.lqh.practice.sb.disruptor.demo.cc.CallEventType;
import com.lqh.practice.sb.disruptor.demo.cc.CallSession;
import com.lqh.practice.sb.disruptor.demo.cc.CallSessionManager;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> 座席接听 CallEventHandler
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/10 0010 18:08
 * @since 2021/6/10 0010 18:08
 */
@Slf4j
public class AnswerHandler7 extends AbstractStateNodeCallEventHandler{
    public AnswerHandler7(CallSessionManager callSessionManager) {
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
            session.put("电话状态", "座席接听");
            session.put("callStatus", getCallState());
            session.put("被叫开始时间", event.getEventTime());
            log.info("座席接听 {}", session);
        }
    }

    @Override
    public boolean support(CallEvent event) {
        return event.getEventType() == CallEventType.ANSWER;
    }

    @Override
    public int getCallState() {
        return StateNode.STATE_AGENT_ANSWER;
    }
}
