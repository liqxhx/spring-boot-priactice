package com.lqh.priactice.springboot.redis.priactice.springboot.mvc.thymeleaf.service;

import com.lqh.priactice.springboot.redis.priactice.springboot.mvc.thymeleaf.entity.User;
import com.lqh.priactice.springboot.redis.priactice.springboot.mvc.thymeleaf.enums.Gender;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p> 类描述: UserService
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/14 22:34
 * @since 2020/07/14 22:34
 */
@Service
public class UserService {
    private static Map<Long, User> repo = new HashMap<>();

    static {
        for (int i = 1; i <= 100 ; i++) {
           User u = User.builder().userId(Long.valueOf((long)i)).gender(i%2==0? Gender.MALE: Gender.FAMALE).age(i).userName("a"+i).password("a"+i).build();
           repo.put(u.getUserId(), u);
        }
        repo.get(Long.valueOf(1)).setIconFace("defface1.jpg");
        repo.get(Long.valueOf(2)).setIconFace("defface2.jpg");
    }

    public Collection<User> list(User query) {
        // [0,10)
        // [10, 20)
        int start = (query.getPage() - 1) * query.getLimit();
        int end = query.getPage()  * query.getLimit();
        Collection<User> users = repo.values().stream().filter((user -> {
            boolean idCondition = true;
            boolean nameCondition = true;
            if(query.getUserId() != null) {
                idCondition = user.getUserId().longValue() == query.getUserId().longValue();
            }
            if(query.getUserName() != null) {
                nameCondition = user.getUserName().indexOf(query.getUserName()) != -1;
            }
            return idCondition && nameCondition;
        })).sorted(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return (int) (o1.getUserId().longValue() - o2.getUserId().longValue());
            }
        }).collect(Collectors.toList());

        List<User> t = (List<User>)users;
        List<User> ret = new ArrayList<>(query.getLimit());
        for(int i = start ; i < end; i++) {
            ret.add(t.get(i));
        }

        return ret;
    }

    public User getUserByUserId(Long userId) {
        return repo.get(userId);
    }

    /**
    * main
    */
    public static void main(String[] args){
        System.out.println(repo);
    }
}
