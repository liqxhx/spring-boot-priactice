package com.lqh.practice.springboot.mongo;

import com.lqh.practice.springboot.mongo.model.CallRecord;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

/**
 * <p> 类描述: MongoTemplateTests
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/05/24 22:53
 * @since 2020/05/24 22:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoBootstrapApplication.class)
public class MongoTemplateTests {
    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void testDelete() {
        Query query=new Query(Criteria.where("id").is(1));
        DeleteResult deleteResult = mongoTemplate.remove(query,CallRecord.class);

    }

    @Test
    public void testQuery() {
        Query query = new Query(Criteria.where("callNo").is("13885581111"));
        CallRecord callRecord = mongoTemplate.findOne(query, CallRecord.class);
    }

    @Test
    public void testUpdate() {
        Query query=new Query(Criteria.where("id").is("some id here"));
        Update update= new Update().set("peerType", "new type").set("bindId", "1");
        //更新查询返回结果集的第一条
        UpdateResult result =mongoTemplate.updateFirst(query, update, CallRecord.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        if(result!=null)
            System.out.println(result.getMatchedCount());
        else
            System.out.println("fail");
    }


    @Test
    public void testCount() {
        System.out.println(mongoTemplate.count(new Query(), CallRecord.class));
    }

    @Test
    public void testSave() {
        CallRecord callRecord = CallRecord.builder()
                .thirdCallId(UUID.randomUUID().toString())
                .callNo("13885581111")
                .ano("13885581111")
                .callType(1)
                .bno("13885581112")
                .peerType(2)
                .peerNo("13885581112")
                .bindId(10001L)
                .thirdBindId("08202006081124241564114777")
                .xno("13885581113")
                .bizId(System.currentTimeMillis()+"")
                .appKey("test")
                .plat(1)
                .cityId(500100)
                .cityName("重庆")
                .provinceId(500000)
                .provinceName("重庆")
                .createTime(new Date())
                .creater("qhlee")
                .updateTime(new Date())
                .updater("qhlee")
                .callStart(0)
                .callDuration(0)
                .callSustainDuration(0)
                .startTime(new Date())
                .finishTime(new Date())
                .callTime(new Date())
                .ringTime(new Date())
                .ringDuration(0)
                .finishState(1)
                .finishType(1)
                .build();



        mongoTemplate.save(callRecord);
    }

}
