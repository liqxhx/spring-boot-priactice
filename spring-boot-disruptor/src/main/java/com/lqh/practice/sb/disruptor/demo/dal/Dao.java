package com.lqh.practice.sb.disruptor.demo.dal;

/**
 * <p> 类描述: Dao
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/09 00:03
 * @since 2021/06/09 00:03
 */
public interface Dao<T> {
    void insert(T e);
    void update(T e);
    void delete(T e);
}
