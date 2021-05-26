package com.liqh.practice.springboot.listener;

import lombok.extern.java.Log;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

/**
 * <p> 类描述: FirstListener
 * 通过spring.factories注册
 * org.springframework.context.ApplicationListener=com.liqh.practice.springboot.listener.FirstListener
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/21 16:35
 * @since 2021/05/21 16:35
 */
@Log
@Order(1)
public class FirstListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("Listener1111111");
    }
}
