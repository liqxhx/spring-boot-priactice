package com.lqh.practice.sb.mybatis.model;

import java.util.Collection;

/**
 * <p> 分页查询结果
 *
 * @author liqh
 * @version 1.0
 * @date 2021/5/28 0028 9:35
 * @since 2021/5/28 0028 9:35
 */
public class PageResult <T>{
    /**
     * 总记录数
     */
    private int totalCount;

    /**
     * 当前第几页 {@link PageParam#getPageNum()}
     */
    private int pageNum;

    /**
     * 每页多少条数据 {@link PageParam#getPageSize()}
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * 数据集
     */
    private Collection<T> data;
}
