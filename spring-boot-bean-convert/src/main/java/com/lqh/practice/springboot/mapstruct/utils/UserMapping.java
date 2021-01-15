package com.lqh.practice.springboot.mapstruct.utils;

import com.alibaba.fastjson.JSON;
import com.lqh.practice.common.domain.User;
import com.lqh.practice.common.domain.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p> 类描述: UserMapping
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/15 20:43
 * @since 2021/01/15 20:43
 */
@Mapper(componentModel = "spring")
public interface UserMapping extends BaseMapping<User, UserVo>  {
    /**
     * MapStruct 提供了时间格式化的属性
     * dataFormat，支持Date、LocalDate、LocalDateTime等时间类型与String的转换
     * @param var1
     * @return
     */
    @Mapping(target = "gender", source = "sex")
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Override
    UserVo sourceToTarget(User var1);

    @Mapping(target = "sex", source = "gender")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Override
    User targetToSource(UserVo var1);

    default List<UserVo.UserConfig> strConfigToListUserConfig(String config) {
        return JSON.parseArray(config, UserVo.UserConfig.class);
    }

    default String listUserConfigToStrConfig(List<UserVo.UserConfig> list) {
        return JSON.toJSONString(list);
    }
}
