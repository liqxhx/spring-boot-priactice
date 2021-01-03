package com.lqh.practice.springboot.redis.pubsub;

import com.lqh.practice.common.domain.Notification;

import java.util.List;

/**
 * <p> 类描述: NotificationListener
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/03 14:21
 * @since 2021/01/03 14:21
 */
public interface INotificationListener {
    List<Integer> listIntereasts();
    void handleNotifcation(Notification notification);
}
