package com.lqh.practice.springboot.cache.simple;

import com.lqh.practice.springboot.cache.common.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <p> 类描述: UserService
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/26 22:08
 * @since 2021/05/26 22:08
 */
@Service
public class UserService2 {
    private Map<Long, User> users1111 = new HashMap<>(16);
    {
        users1111.put(2L, new User(2L, "aaa"));
        users1111.put(3L, new User(3L, "bbb"));
    }

    @Resource(name = "simpleCacheManager")
    private CacheManager cacheManager;

    @PostConstruct
    public void init() {
        users1111.entrySet().forEach(e -> {
            cacheManager.getCache("users").put(e.getKey(), e.getValue());
        });

    }

    public User getUser(User u) {
        System.out.println("查询 User by Id:" +  u.getId());

        Cache.ValueWrapper w = getCache().get(u.getId());
        if(w == null) {
            w = new SimpleValueWrapper(loadFromDB(u.getId()));
            getCache().put(u.getId(), w.get());
        }

        return (User) w.get();

    }

    private User loadFromDB(Long id) {
        System.out.println("从数据库加载" + id);
        return new User(id, "");
    }

    private Cache getCache() {
        return cacheManager.getCache("users");
    }

}
