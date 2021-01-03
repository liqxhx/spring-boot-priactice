package com.lqh.practice.common.domain;

/**
 * <p> 类描述: ILifeCycle
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/03 14:25
 * @since 2021/01/03 14:25
 */
public interface ILifeCycle {
    void start() throws Exception;
    void stop();

    void create() throws Exception;
    void destroy();
}
