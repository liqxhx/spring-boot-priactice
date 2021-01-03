package com.lqh.practice.springboot.mongo;

import com.lqh.practice.springboot.mongo.model.CallRecord;
import com.lqh.practice.springboot.mongo.repository.CallRecordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p> 类描述: CallRecordRepositoryTests
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/08/21 18:03
 * @since 2020/08/21 18:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CallRecordRepositoryTests {
    @Autowired
    private CallRecordRepository callRecordRepository;
    @Test
    public void testSaveCallRecord() {
        CallRecord record = CallRecord.builder().build();
        record = callRecordRepository.save(record);
    }

    @Test
    public void testFind() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testUpdate() {
    }

    /**
     * Pageable 是 Spring Data 库中定义的一个接口，该接口是所有分页相关信息的一个抽象，
     * 通过该接口，可以得到和分页相关所有信息（如 pageNumber、pageSize 等），
     * 这样，JPA 就能够通过 pageable 参数来得到一个带分页信息的 SQL 语句。
     *
     * Page 类也是 Spring Data 提供的一个接口，
     * 该接口表示一部分数据的集合以及其相关的下一部分数据、数据总数等相关信息，
     * 通过该接口，可以得到数据的总体信息（数据总数、总页数…）以及当前数据的信息（当前数据的集合、当前页数等）
     */
    @Test
    public void testPage() {
        // new Sort(Sort.Direction.DESC, "id");
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(2, 10, sort);
        Page page=callRecordRepository.findAll(pageable);
        System.out.println("users: "+page.getContent().toString());
    }
}
