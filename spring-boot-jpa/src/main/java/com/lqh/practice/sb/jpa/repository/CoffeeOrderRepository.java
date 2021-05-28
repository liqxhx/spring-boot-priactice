package com.lqh.practice.sb.jpa.repository;

import com.lqh.practice.sb.jpa.model.CoffeeOrder;

import java.util.List;

/**
 * <p> 类描述: CoffeeOrderRepository
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/28 22:14
 * @since 2021/05/28 22:14
 */
//public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {
public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {
    /**
     * 根据 顾客姓名查询
     * @param customer
     * @return
     */
    List<CoffeeOrder> findByCustomerOrderById(String customer);

    /**
     * 根据coffee名查询
     * @param name
     * @return
     */
    List<CoffeeOrder> findByItems_Name(String name);
}
