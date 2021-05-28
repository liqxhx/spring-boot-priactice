package com.lqh.practice.sb.mybatis;

import com.lqh.practice.sb.mybatis.mapper.CoffeeMapper;
import com.lqh.practice.sb.mybatis.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p> 类描述: MybatisApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/28 23:14
 * @since 2021/05/28 23:14
 */
@SpringBootApplication
@MapperScans(
        @MapperScan(basePackages = "com.lqh.practice.sb.mybatis.mapper")
)
@Slf4j
public class MybatisAnnoApplication implements ApplicationRunner {
    @Autowired
    private CoffeeMapper coffeeMapper;
    
    /**
    * main
    */
    public static void main(String[] args){
        SpringApplication.run(MybatisAnnoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30))
                .build();
        int effectLines = coffeeMapper.save(latte);
        log.info("save {} coffee: {}", effectLines, latte);

        Coffee c = coffeeMapper.findById(2L);
        log.info("coffee : {}", c);
    }
}
