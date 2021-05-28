package com.lqh.practice.sb.jpa.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p> 类描述: Coffee
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/28 21:00
 * @since 2021/05/28 21:00
 */
@Entity
@Table(name = "t_menu")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Coffee extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 487778812052563520L;

    @Column(columnDefinition = "varchar(32) comment '名称'")
    private String name;

    @Column
    // org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount 以bigint存儲
    @Type(type="org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",
        parameters = {@org.hibernate.annotations.Parameter(name="currencyCode", value="CNY")})
    private Money price;
}
