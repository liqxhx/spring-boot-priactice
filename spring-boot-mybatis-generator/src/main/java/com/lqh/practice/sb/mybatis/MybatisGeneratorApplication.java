package com.lqh.practice.sb.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
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
public class MybatisGeneratorApplication implements ApplicationRunner {
    /**
    * main
    */
    public static void main(String[] args){
        SpringApplication.run(MybatisGeneratorApplication.class, args);
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        generate();
    }

    private void generate() throws Exception {
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(
                this.getClass().getResourceAsStream("/generatorConfig.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
