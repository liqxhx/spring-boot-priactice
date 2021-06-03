package com.lqh.practice.sb.dict;

import com.lqh.practice.sb.dict.core.DictRepository;
import com.lqh.practice.sb.dict.model.DictEntity;
import com.lqh.practice.sb.dict.model.DictManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p> DictServiceApplication
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/2 18:24
 * @since 2021/6/2 18:24
 */
@SpringBootApplication
@Log4j2
public class DictServiceApplication implements ApplicationRunner {
    @Autowired
    DictRepository dictRepository;

    @Autowired
    DictManager dictManager;

    public static void main(String[] args) {
        SpringApplication.run(DictServiceApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        dictRepository.findAll().forEach(System.out::println);
        log.info(dictManager.supportFieldNames());

        DictEntity dictEntity = dictManager.get("NA");
        log.info(dictEntity.codeMap());
        log.info(dictEntity.nameMap());
    }
}
