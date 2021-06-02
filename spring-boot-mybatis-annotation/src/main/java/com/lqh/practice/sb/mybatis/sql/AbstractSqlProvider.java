package com.lqh.practice.sb.mybatis.sql;

import org.apache.ibatis.jdbc.SQL;

/**
 * <p> sql provider
 *
 * @author liqh
 * @version 1.0
 * @date 2021/5/27 18:04
 * @since 2021/5/27 18:04
 */
public abstract class AbstractSqlProvider<E, Q> {
    /**
     * 表名称
     *
     * @return String
     */
    abstract String tableName();

    public String insert(E e) {
//        SQL sql = createSql();
//        sql.INSERT_INTO(tableName());
//        valuesFiled(sql, e);
//        return sql.toString();
        return null;
    }

    public String deleteByKey(Integer id) {
//        SQL sql = createSql();
//        sql.DELETE_FROM(tableName());
//        sql.WHERE(whereId());
//        return sql.toString();
        return null;
    }

    public String update(E e) {
//        SQL sql = createSql();
//        sql.UPDATE(tableName());
//        setFiled(sql, e);
//        sql.WHERE(whereId());
//        return sql.toString();
        return null;
    }

    public String findByKey() {
//        SQL sql = createSql();
//        sql.SELECT(columnAlias());
//        sql.FROM(tableName());
//        sql.WHERE(whereId());
//        return sql.toString();
        return null;
    }

    protected SQL createSql() {
        return new SQL();
    }
}
