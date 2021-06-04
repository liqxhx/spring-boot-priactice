package com.lqh.practice.sb.sj.service;

import com.lqh.practice.sb.sj.entity.OrderItem;
import com.lqh.practice.sb.sj.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class OrderItemService {
    @Resource
    OrderItemMapper orderItemMapper;

    public void insert() {
        for (int i = 1; i <= 100; i++) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItemId(i);
            orderItem.setOrderId(i);
            orderItem.setUserId(i);
            orderItem.setCreateTime(new Date());

            orderItemMapper.insert(orderItem);
        }
    }

    public OrderItem getOrderItemByItemId(Integer id){
        return orderItemMapper.selectByPrimaryKey(id);
    }

}
