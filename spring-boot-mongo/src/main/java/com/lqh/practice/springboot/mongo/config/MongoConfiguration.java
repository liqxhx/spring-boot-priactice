package com.lqh.practice.springboot.mongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * <p> 类描述: MongoConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/08/21 17:35
 * @since 2020/08/21 17:35
 */
@Configuration
public class MongoConfiguration {
    @Value("${mongodb.uri}")
    private String mongodbUri;

    @Primary
    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) throws Exception {
        MappingMongoConverter converter =
                new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return new MongoTemplate(mongoDbFactory, converter);
    }

    @Bean
    @Primary
    public MongoDbFactory mongoFactory() throws Exception {
        MongoClient client =  MongoClients.create(mongodbUri);
        return new SimpleMongoClientDbFactory(client, "test");
    }
}
