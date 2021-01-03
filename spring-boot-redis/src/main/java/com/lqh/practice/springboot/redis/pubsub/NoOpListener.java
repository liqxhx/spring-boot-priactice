package com.lqh.practice.springboot.redis.pubsub;

import com.lqh.practice.common.domain.Notification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p> 类描述: NoopsListener
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/03 15:08
 * @since 2021/01/03 15:08
 */
@Component
public class NoOpListener implements INotificationListener {
    @Override
    public List<Integer> listIntereasts() {
        return Arrays.asList(Notification.PING);
    }

    @Override
    public void handleNotifcation(Notification notification) {
        println(notification);
    }

    private void println(Notification notification) {
        System.out.println(String.format("[%s]-%s", LocalDateTime.now().toString(), notification));
    }
}
