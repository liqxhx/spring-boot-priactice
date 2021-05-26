package com.liqh.practice.springboot.listener;

import lombok.extern.java.Log;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.annotation.Order;

/**
 * <p> 类描述: FourthListener
 * 注册方式随意选一种 同FirstListener、Second、Third
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/21 16:35
 * @since 2021/05/21 16:35
 */
@Log
@Order(4)
public class FourthListener implements SmartApplicationListener {

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return ApplicationStartedEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("Listener4444444");
    }
}
