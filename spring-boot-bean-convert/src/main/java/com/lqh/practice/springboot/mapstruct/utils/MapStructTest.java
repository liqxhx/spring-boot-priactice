package com.lqh.practice.springboot.mapstruct.utils;

import com.lqh.practice.common.domain.User;
import com.lqh.practice.common.domain.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

/**
 * <p> 类描述: MapStructTest
 * 当两个对象属性不一致时，比如User对象中某个字段不存在与UserVo当中时，在编译时会有警告提示，可以在@Mapping中配置 ignore = true，当字段较多时，可以直接在@Mapper中设置unmappedTargetPolicy属性或者unmappedSourcePolicy属性为 ReportingPolicy.IGNORE即可。
 * 如果项目中也同时使用到了 Lombok，一定要注意 Lombok的版本要等于或者高于1.18.10，否则会有编译不通过的情况发生，笔者掉进这个坑很久才爬了出来，希望各位不要重复踩坑。
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/15 20:50
 * @since 2021/01/15 20:50
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapStructTest {
    @Resource
    private UserMapping userMapping;

    @Test
    public void tetDomain2DTO() {
        User user = new User()
                .setId(1L)
                .setUsername("zhangsan")
                .setSex(1)
                .setPassword("abc123")
                .setCreateTime(LocalDateTime.now())
                .setBirthday(LocalDate.of(1999, 9, 27))
                .setConfig("[{\"field1\":\"Test Field1\",\"field2\":500}]");
        UserVo userVo = userMapping.sourceToTarget(user);
        log.info("User: {}", user);
        //        User: User(id=1, username=zhangsan, password=abc123, sex=1, birthday=1999-09-27, createTime=2020-01-17T17:46:20.316, config=[{"field1":"Test Field1","field2":500}])
        log.info("UserVo: {}", userVo);
        //        UserVo: UserVo(id=1, username=zhangsan, gender=1, birthday=1999-09-27, createTime=2020-01-17 17:46:20, config=[UserVo.UserConfig(field1=Test Field1, field2=500)])
    }

    @Test
    public void testDTO2Domain() {
        UserVo.UserConfig userConfig = new UserVo.UserConfig();
        userConfig.setField1("Test Field1");
        userConfig.setField2(500);

        UserVo userVo = new UserVo()
                .setId(1L)
                .setUsername("zhangsan")
                .setGender(2)
                .setCreateTime("2020-01-18 15:32:54")
                .setBirthday(LocalDate.of(1999, 9, 27))
                .setConfig(Collections.singletonList(userConfig));
        User user = userMapping.targetToSource(userVo);
        log.info("UserVo: {}", userVo);
        //        UserVo: UserVo(id=1, username=zhangsan, gender=2, birthday=1999-09-27, createTime=2020-01-18 15:32:54, config=[UserVo.UserConfig(field1=Test Field1, field2=500)])
        log.info("User: {}", user);
        //        User: User(id=1, username=zhangsan, password=null, sex=2, birthday=1999-09-27, createTime=2020-01-18T15:32:54, config=[{"field1":"Test Field1","field2":500}])
    }
}
