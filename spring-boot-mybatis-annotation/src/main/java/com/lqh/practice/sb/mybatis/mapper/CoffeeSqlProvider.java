package com.lqh.practice.sb.mybatis.mapper;

import org.apache.ibatis.jdbc.SQL;

/**
 * <p> 类描述: CoffeeSqlProvider
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/28 23:35
 * @since 2021/05/28 23:35
 */
public final class CoffeeSqlProvider {
    public String findById() {
       return new SQL() {{
           SELECT("*");
           FROM("t_coffee");
           WHERE("id = #{id}");
       }} .toString();
    }
}
