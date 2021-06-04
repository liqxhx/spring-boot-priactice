package com.lqh.practice.sb.sj.entity;

import lombok.Data;

import java.util.Date;

@Data
public class OrderItem {
    private Integer itemId;

    private Integer orderId;

    private Integer userId;
    private Date createTime;
}