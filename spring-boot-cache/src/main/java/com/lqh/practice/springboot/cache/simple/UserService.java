package com.lqh.practice.springboot.cache.simple;

import com.lqh.practice.springboot.cache.common.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <p> 类描述: UserService
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/26 22:08
 * @since 2021/05/26 22:08
 */
@Service
// spring4.1需要一个一个重复配置 cacheNames = "users", keyGenerator = "userKeyGenerator", cacheManager = "simpleCacheManager
// 使用 CacheConfig可以简化配置
@CacheConfig(cacheNames = "users", keyGenerator = "userKeyGenerator", cacheManager = "simpleCacheManager")
public class UserService {
    Long seq = 10000L;

//    @Cacheable(cacheNames = "users", key="'user-id-'+#id" )
//    @Cacheable(cacheNames = {"users", "default"}, key="'user-id-'+#id")
//    @Cacheable(cacheNames = "users", keyGenerator = "userKeyGenerator")
//    @Cacheable(cacheNames = "users", keyGenerator = "userKeyGenerator")
    @Cacheable
    public User getUser(/*Long id*/ User u) {
        System.out.println("执行 getUserById:" + u.getId());
        return loadFromDB(u.getId());
    }

//    @CachePut(cacheNames = "users", key="'user-id-'+#u.id")
//    @CachePut(cacheNames = "users", keyGenerator = "userKeyGenerator")
    @CachePut
    public User saveOrUpdate(User u) {
        System.out.println("执行 saveOrUpdate");
        if(u.getId() == null) {
            u.setId(seq++);
        }
        return u;
    }

//    @CacheEvict(cacheNames = "users", key="'user-id-'+#u.id")
//    @CacheEvict(cacheNames = "users",  keyGenerator = "userKeyGenerator")
    @CacheEvict
    public void remove(User u) {
        System.out.println("执行 从数据库删除User:" + u);
    }

    private User loadFromDB(Long id) {
        System.out.println("执行 loadFromDB:" + id);
        return new User(id, "");
    }
}
