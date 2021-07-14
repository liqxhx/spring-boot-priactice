package com.lqh.practice.sb.disruptor.demo.cc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p> CallSessionManager
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/9 15:08
 * @since 2021/6/9 15:08
 */
public class CallSessionManager {
    private final Map<String, CallSession> callSessionMap = new ConcurrentHashMap<>(16);

    public CallSession newSession(String sessionId) {
        return callSessionMap.computeIfAbsent(sessionId, (sid) -> new CallSession(sid));
    }

    public CallSession getSession(String sessionId) {
        return this.callSessionMap.get(sessionId);
    }

    public CallSession removeSession(String sessionId) {
        return this.callSessionMap.remove(sessionId);
    }
}
