package com.liqh.practice.springboot.listener;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

/**
 * <p> 类描述: SecondListener
 * 通过{@link SpringApplication#addListeners}注册
 * context.listener.classes=com.liqh.practice.springboot.listener.SecondListener
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/21 16:35
 * @since 2021/05/21 16:35
 */
@Log
@Order(2)
public class SecondListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("Listener2222222");
    }
}
