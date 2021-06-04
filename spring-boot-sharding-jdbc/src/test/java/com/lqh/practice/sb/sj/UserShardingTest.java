package com.lqh.practice.sb.sj;

import com.lqh.practice.sb.sj.entity.UserInfo;
import com.lqh.practice.sb.sj.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 演示取模的分库分表策略
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "com.lqh.practice.sb.sj.mapper")
public class UserShardingTest {
	@Resource
	UserService userService;

	@Test
	public void insert(){

		userService.insert();
	}

	@Test
	public void select(){
		UserInfo userInfo1= userService.getUserInfoByUserId(1L);
		System.out.println("------userInfo1:"+userInfo1);

        UserInfo userInfo2= userService.getUserInfoByUserId(2L);
        System.out.println("------userInfo2:"+userInfo2);
	}










/*	@Test
	public void selectByRange(){
		Map map = new HashMap<>();
		Long firstId = 10L;
		Long lastId = 20L;
		map.put(firstId, lastId);
		List<UserInfo> list = userService.selectByRange(10L,20L);
		System.out.println(list);
	}*/

}
