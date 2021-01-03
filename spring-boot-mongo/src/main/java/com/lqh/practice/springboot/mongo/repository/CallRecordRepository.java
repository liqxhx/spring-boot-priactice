package com.lqh.practice.springboot.mongo.repository;

import com.lqh.practice.springboot.mongo.model.CallRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <p> 类描述: CallRecordRepository
 * MongoRepository 继承于 PagingAndSortingRepository，再往上就是 CrudRepository
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/08/21 18:01
 * @since 2020/08/21 18:01
 */
public interface CallRecordRepository extends MongoRepository<CallRecord, String> {
}
