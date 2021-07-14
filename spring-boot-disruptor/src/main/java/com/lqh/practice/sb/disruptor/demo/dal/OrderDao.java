package com.lqh.practice.sb.disruptor.demo.dal;


import lombok.extern.slf4j.Slf4j;

/**
 * <p> 类描述: OrderDao
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/09 00:10
 * @since 2021/06/09 00:10
 */
@Slf4j
public class OrderDao implements Dao<Order>{

    @Override
    public void insert(Order e) {
//        if(e.getId() == 10) {
//            throw new IllegalArgumentException();
//        }
        log.debug("insert order {}", e);
    }

    @Override
    public void update(Order e) {
        log.debug("update order {}", e);
    }

    @Override
    public void delete(Order e) {
        log.debug("delete order {}", e);
    }
}
