package com.lqh.priactice.springboot.task.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p> 类描述: SimpleTask
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/11/06 22:34
 * @since 2020/11/06 22:34
 */
@Component
public class SimpleTask {
    @Scheduled(fixedDelay = 1000, initialDelay = 0)
    @Async
    public void printJob() {
        println("print job run");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Scheduled(fixedDelay = 1000, initialDelay = 0)
    public void printJob2() {
        println("print job2 run");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void println(String content){
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+" "+Thread.currentThread().getName()+": "+content);
    }
}
