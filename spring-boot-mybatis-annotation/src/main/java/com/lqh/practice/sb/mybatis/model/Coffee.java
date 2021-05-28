package com.lqh.practice.sb.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.util.Date;

/**
 * <p> 类描述: Coffee
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/28 23:22
 * @since 2021/05/28 23:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffee {
    private Long id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
