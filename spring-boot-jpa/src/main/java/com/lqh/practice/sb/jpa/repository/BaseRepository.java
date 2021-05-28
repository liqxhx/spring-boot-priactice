package com.lqh.practice.sb.jpa.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * <p> 类描述: BaseRepository
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/28 22:26
 * @since 2021/05/28 22:26
 */
@NoRepositoryBean
public interface BaseRepository<T, Long> extends PagingAndSortingRepository<T, Long> {
    /**
     * findTop3By OrderBy UpdateTimeDesc IdAsc
     * @return
     */
    List<T> findTop3ByOrderByUpdateTimeDescIdAsc();
}
