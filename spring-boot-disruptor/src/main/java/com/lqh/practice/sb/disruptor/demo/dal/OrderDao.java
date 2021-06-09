package com.lqh.practice.sb.disruptor.demo.dal;


import com.lqh.practice.sb.disruptor.demo.dal.entity.Order;
import com.lqh.practice.sb.disruptor.gettingstart.Printer;

/**
 * <p> 类描述: OrderDao
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/09 00:10
 * @since 2021/06/09 00:10
 */
public class OrderDao implements Dao<Order>{

    @Override
    public void insert(Order e) {
//        if(e.getId() == 10) {
//            throw new IllegalArgumentException();
//        }
        Printer.output("insert order :" + e);
    }

    @Override
    public void update(Order e) {
        Printer.output("update order :" + e);
    }

    @Override
    public void delete(Order e) {
        Printer.output("delete order :" + e);
    }
}
