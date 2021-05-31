package com.lqh.practice.sb.mybatis;

import com.github.pagehelper.PageInfo;
import com.lqh.practice.sb.mybatis.mapper.CoffeeMapper;
import com.lqh.practice.sb.mybatis.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * <p> 类描述: MybatisApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/28 23:14
 * @since 2021/05/28 23:14
 */
@SpringBootApplication
@Slf4j
@MapperScan(basePackages = "com.lqh.practice.sb.mybatis.mapper")
public class MybatisXmlApplication implements ApplicationRunner {
    @Autowired
    CoffeeMapper coffeeMapper;
    /**
    * main
    */
    public static void main(String[] args){
        SpringApplication.run(MybatisXmlApplication.class, args);
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {

//        Coffee latte = new Coffee();
//        latte.setName("latte");
//        latte.setPrice(Money.of(CurrencyUnit.of("CNY"), 15));
//        coffeeMapper.insert(latte);
//        System.out.println(latte);

//        coffeeMapper.selectAll().forEach(System.out::println);
//        System.err.println("===============================");

        coffeeMapper.findAllWithRowBounds(new RowBounds(1, 3)).forEach(c -> log.info("page 1. c:{}", c));
        System.err.println("===============================");
        coffeeMapper.findAllWithRowBounds(new RowBounds(2, 3)).forEach(c -> log.info("page 2. c:{}", c));
        System.err.println("===============================");
        coffeeMapper.findAllWithRowBounds(new RowBounds(1, 0)).forEach(c -> log.info("page 1. c:{}", c));

        System.err.println("===============================");
        List<Coffee> list = coffeeMapper.findAllWithParam(1, 3);
        list.forEach(c -> log.info("page 1, c:{}", c));
        System.err.println("===============================");
        list = coffeeMapper.findAllWithParam(2, 3);
        PageInfo<Coffee> pageInfo = new PageInfo<>(list);
        log.info("pageInfo:{}", pageInfo);

    }

}
