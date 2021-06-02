package com.lqh.practice.sb.mybatis;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.ClassPathResource;

public abstract class BaseMapper {

    public <T> T getMapper(Class<T> mapperClass, String mapperXml) throws Exception {
        // 数据源注入
        UnpooledDataSource dataSource = new UnpooledDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setDriver("com.mysql.jdbc.Driver");

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        Environment environment = new Environment("test", new JdbcTransactionFactory(), dataSource);
        Configuration codeConfig = new Configuration(environment);
        sqlSessionFactoryBean.setConfiguration(codeConfig);
        ClassPathResource classPathResource = new ClassPathResource(mapperXml);
        ClassPathResource[] arr = {classPathResource};
        sqlSessionFactoryBean.setMapperLocations(arr);

        sqlSessionFactoryBean.setDataSource(dataSource);

        // 初始化
        sqlSessionFactoryBean.afterPropertiesSet();

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();

        Configuration configuration = sqlSessionFactory.getConfiguration();

        if (!configuration.hasMapper(mapperClass)) {
            configuration.addMapper(mapperClass);
        }

        return (T) configuration.getMapper(mapperClass, sqlSessionFactory.openSession());
    }
}