package com.lqh.practice.sb.disruptor.demo.cc.handler;

import com.lqh.practice.sb.disruptor.demo.cc.CallEvent;
import com.lqh.practice.sb.disruptor.demo.cc.CallEventType;
import com.lqh.practice.sb.disruptor.demo.cc.CallSession;
import com.lqh.practice.sb.disruptor.demo.cc.CallSessionManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * <p> 类描述: 到系统 CallEventHandler
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/08 22:34
 * @since 2021/06/08 22:34
 */
@Slf4j
public class ArrivedHandler1 extends AbstractStateNodeCallEventHandler {

    public ArrivedHandler1(CallSessionManager callSessionManager) {
        super(callSessionManager);
    }

    @Override
    public int getCallState() {
        return StateNode.STATE_ARRIVED;
    }

    @Override
    public boolean support(CallEvent event) {
        return CallEventType.INVITE == event.getEventType();
    }

    @Override
    public void handleCallEvent(CallEvent event) {
        // 黑名单校验
        log.debug("收到事件:{}", event);

        if(new Random().nextInt()%3 == 0 ) {
            log.warn("黑名单校验不过:{}", event);
        } else {

            log.info("黑名单校验通过:{}", event);
            log.info("发送accept指令:{}", event);
            CallSession callSession = callSessionManager.newSession(event.getSessionId());
            callSession.put("主叫开始时间", System.currentTimeMillis());
            callSession.put("主叫号码", event.getCaller());
            callSession.put("被叫号码", event.getCallee());
            callSession.put("电话状态", "到系统");
            callSession.put("callStatus", getCallState());


            log.info("创建会话:{}", callSession);
        }
    }
}
