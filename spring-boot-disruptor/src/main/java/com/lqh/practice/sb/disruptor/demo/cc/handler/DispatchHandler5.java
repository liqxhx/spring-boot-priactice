package com.lqh.practice.sb.disruptor.demo.cc.handler;

import com.lqh.practice.sb.disruptor.demo.cc.CallEvent;
import com.lqh.practice.sb.disruptor.demo.cc.CallEventType;
import com.lqh.practice.sb.disruptor.demo.cc.CallSession;
import com.lqh.practice.sb.disruptor.demo.cc.CallSessionManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p> 分配 CallEventHandler
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/10 0010 17:23
 * @since 2021/6/10 0010 17:23
 */
@Slf4j
public class DispatchHandler5 extends AbstractStateNodeCallEventHandler {
    public DispatchHandler5(CallSessionManager callSessionManager) {
        super(callSessionManager);
    }

    @Override
    protected void handleCallEvent(CallEvent event) {
        CallSession session = callSessionManager.getSession(event.getSessionId());

        if(session == null) {
            log.warn("来电丢失:{}", event);
            return;
        }
        log.info("分配开始 {}", session);
        session.put("分配开始时间", System.currentTimeMillis());
        session.put("电话状态", "分配");
        session.put("callStatus", getCallState());

        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
        }

        if(new Random().nextBoolean()) {
            session.put("分配成功标记", true);
            session.put("分配结束时间", System.currentTimeMillis());
            log.info("执行connect转换到座席 {}", session);
        } else {
            session.put("分配成功标记", false);
            session.put("分配结束时间", System.currentTimeMillis());
            log.info("分配失败 执行clear 挂断来电 {}", session);
        }


        log.info("分配结束 {}", session);
    }

    @Override
    public boolean support(CallEvent event) {
        return event.getEventType() == CallEventType.INCOMING;
    }

    @Override
    public int getCallState() {
        return StateNode.STATE_DISPATCH;
    }
}
