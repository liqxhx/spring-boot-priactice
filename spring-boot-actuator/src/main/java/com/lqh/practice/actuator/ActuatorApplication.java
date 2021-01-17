package com.lqh.practice.actuator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * <p> 类描述: ActuatorApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/12/16 20:27
 * @since 2020/12/16 20:27
 */
@SpringBootApplication
@Slf4j
public class ActuatorApplication {
    /**
    * main
    */
    public static void main(String[] args){
        SpringApplication.run(ActuatorApplication.class, args);
    }

    @Scheduled(cron="0/20 * * * * ?")
    public void run20s() {
        log.info("#schedule:{}", LocalDateTime.now());
    }
}
