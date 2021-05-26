package com.liqh.practice.springboot.listener;

import lombok.extern.java.Log;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

/**
 * <p> 类描述: ThirdListener
 * 通过配置文件 application.properties 注册
 *
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/21 16:35
 * @since 2021/05/21 16:35
 */
@Log
@Order(3)
public class ThirdListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("Listener3333333");
    }
}
