package com.lqh.practice.springboot.redis.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 类描述: PubsubManager
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/03 13:56
 * @since 2021/01/03 13:56
 */
@Service
public class NotificationManager {
    private Map<Integer, List<INotificationListener>> notificationListeners;

    public void onNotification(String notificationMessage) {
        System.out.println("received: " + notificationMessage);
//        Optional.ofNullable(notificationListeners.get(notification.getType())).ifPresent(handlers -> {
//            handlers.forEach(handler -> handler.handleNotifcation(notification));
//        });
    }

    @Autowired
    public void init(List<INotificationListener> notifications) {
        notificationListeners = new HashMap<>();

       notifications.forEach(listener -> {
           listener.listIntereasts().forEach(type -> {
               List<INotificationListener> ls = notificationListeners.get(type);
               if(ls == null) {
                   ls = new ArrayList<>(4);
                   notificationListeners.put(type, ls);
               }
               ls.add(listener);
           });
       });

        System.out.println(notificationListeners);
    }
}
