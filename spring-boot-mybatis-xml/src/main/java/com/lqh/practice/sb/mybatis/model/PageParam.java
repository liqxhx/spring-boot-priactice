package com.lqh.practice.sb.mybatis.model;

import lombok.Data;

/**
 * <p> PageParam
 *
 * @author liqh
 * @version 1.0
 * @date 2021/5/28 9:33
 * @since 2021/5/28 9:33
 */
@Data
public class PageParam {
    /**
     * 当前第几页，pageIndex
     * */
    private int pageNum;

    /**
     * 每页多少条数据
     */
    private int pageSize;

    /**
     * todo 最大多少页
     */
    private int maxPageNum;
}
