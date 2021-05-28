package com.lqh.practice.sb.mybatis.mapper;

import com.lqh.practice.sb.mybatis.model.Coffee;
import org.apache.ibatis.annotations.*;

/**
 * <p> 类描述: CoffeeMapper
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/28 23:33
 * @since 2021/05/28 23:33
 */
@Mapper
public interface CoffeeMapper {
    @Insert("insert into t_coffee (name, price, create_time, update_time)"
            + "values (#{name}, #{price}, now(), now())")
    // useGeneratedKeys：是否返回生成的主键 keyProperty：传入对象中的属性名 keyColumn：数据库中的字段名
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(Coffee coffee);

//    @Select("select * from t_coffee where id = #{id}")
//    @Results({
//            @Result(id = true, column = "id", property = "id"),
//            @Result(column = "create_time", property = "createTime"),
//            // map-underscore-to-camel-case = true 可以实现一样的效果
//            // @Result(column = "update_time", property = "updateTime"),
//    })
    @SelectProvider(type = CoffeeSqlProvider.class, method = "findById")
    Coffee findById(@Param("id") Long id);
}
