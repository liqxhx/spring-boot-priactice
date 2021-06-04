package com.lqh.practice.sb.sj.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Order implements Serializable {
    private Integer orderId;

    private Integer userId;

    private Date createTime;
}