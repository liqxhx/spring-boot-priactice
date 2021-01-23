package com.lqh.practice.springboot.datasource.multi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * <p> 类描述: MitiDatasourceConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/23 22:05
 * @since 2021/01/23 22:05
 */
@Slf4j
@Configuration
public class MultiDatasourceConfiguration {
    @Value("${bar.datasource.type:com.alibaba.druid.pool.DruidDataSource}")
    private Class<? extends DataSource> masterDataSourceType;

    @Bean
    @ConfigurationProperties("foo.datasource")
    public DataSourceProperties fooDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource fooDataSource() {
        DataSourceProperties dataSourceProperties = fooDataSourceProperties();
        log.info("foo datasource: {}", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public PlatformTransactionManager fooTxManager(DataSource fooDataSource) {
        return new DataSourceTransactionManager(fooDataSource);
    }

    @Bean
    @ConfigurationProperties(prefix = "bar.datasource")
    public DataSource barDataSource() {
        return DataSourceBuilder.create().type(masterDataSourceType).build();
    }


//    @Bean
//    @ConfigurationProperties("bar.datasource")
//    public DataSourceProperties barDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    public DataSource barDataSource() {
//        DataSourceProperties dataSourceProperties = barDataSourceProperties();
//        log.info("bar datasource: {}", dataSourceProperties.getUrl());
//        return dataSourceProperties.initializeDataSourceBuilder().build();
//    }

    @Bean
    @Resource
    public PlatformTransactionManager barTxManager(DataSource barDataSource) {
        return new DataSourceTransactionManager(barDataSource);
    }
}
