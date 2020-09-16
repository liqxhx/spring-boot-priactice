package com.lqh.priactice.spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * <p> 类描述: ScheduleConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/10 16:45
 * @since 2020/09/10 16:45
 */
@EnableScheduling
@Configuration
public class ScheduleConfiguration implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                r -> {
                    Thread t = new Thread(r);
                    t.setName("my-schedule-" + t.getName());
                    return t;
                }));
    }

}
