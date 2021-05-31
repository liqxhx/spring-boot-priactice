package com.lqh.practice.sb.mybatis.mapper;

import com.lqh.practice.sb.mybatis.model.Coffee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CoffeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Coffee record);

    int insertSelective(Coffee record);

    Coffee selectByPrimaryKey(Long id);

    List<Coffee> selectAll();

    @Select("select * from t_coffee order by id")
    List<Coffee> findAllWithRowBounds(RowBounds rowBounds);

    @Select("select * from t_coffee order by id")
    List<Coffee> findAllWithParam(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    int updateByPrimaryKeySelective(Coffee record);

    int updateByPrimaryKey(Coffee record);
}