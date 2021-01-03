package com.lqh.priactice.springboot.task.schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p> 类描述: SimpleTaskConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/11/06 22:35
 * @since 2020/11/06 22:35
 */
@EnableScheduling
@EnableAsync
@Configuration
public class SimpleTaskConfiguration implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(new ScheduledThreadPoolExecutor(30,
                (r) -> {
                    Thread t = new Thread(r);
                    t.setName("t1-" + t.getName());
                    return t;
                }));
    }

    @Bean
    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
        asyncTaskExecutor.setMaxPoolSize(8);
        asyncTaskExecutor.setCorePoolSize(4);
        asyncTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        asyncTaskExecutor.setKeepAliveSeconds(10);
        asyncTaskExecutor.setQueueCapacity(1);
        asyncTaskExecutor.setThreadNamePrefix("t2-");
        asyncTaskExecutor.setThreadPriority(Thread.NORM_PRIORITY);
        asyncTaskExecutor.initialize();
        return asyncTaskExecutor;
    }
}
