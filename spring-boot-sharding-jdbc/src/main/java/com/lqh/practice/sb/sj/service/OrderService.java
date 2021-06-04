package com.lqh.practice.sb.sj.service;

import com.lqh.practice.sb.sj.entity.Order;
import com.lqh.practice.sb.sj.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


@Service
public class OrderService {
    @Resource
    OrderMapper orderMapper;

    public static Long orderId = 1L;
    public static Long userId = 1L;

    public void insert() {
        for (int i = 1; i <= 100; i++) {
            Order order = new Order();
            order.setOrderId(i);
            order.setUserId(i);
            order.setCreateTime(new Date());
            orderId++;
            userId++;
            orderMapper.insert(order);
        }
    }

    public Order getOrderInfoByOrderId(Integer id){
        return orderMapper.selectByPrimaryKey(id);
    }

}
