package com.lqh.practice.sb.disruptor.demo.dal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> 类描述: PersistenceEvent
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/06/08 23:45
 * @since 2021/06/08 23:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersistenceEvent {
    public static final int OP_INSERT = 1;
    public static final int OP_UPDATE = 2;
    public static final int OP_DELETE = 3;

    private int opType;
    private Object entity;
}
