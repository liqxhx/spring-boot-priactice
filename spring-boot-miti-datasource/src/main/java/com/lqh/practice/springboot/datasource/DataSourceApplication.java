package com.lqh.practice.springboot.datasource;

import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * <p> 类描述: DataSourceApplication
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/23 22:13
 * @since 2021/01/23 22:13
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class})
@Slf4j
public class DataSourceApplication implements ApplicationRunner {

    @Resource(name="fooDataSource")
    private DataSource fooDataSource;

    @Resource(name="barDataSource")
    private DataSource barDataSource;
    /**
    * main
    */
    public static void main(String[] args){
        SpringApplication.run(DataSourceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("fooDataSource: " + fooDataSource);

        System.out.println("barDataSource: " + barDataSource);
    }
}
