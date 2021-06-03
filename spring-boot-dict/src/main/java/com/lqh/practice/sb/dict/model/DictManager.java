package com.lqh.practice.sb.dict.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lqh.practice.sb.dict.core.Dict;
import com.lqh.practice.sb.dict.core.DictRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DictManager
 * @author liqh
 * @version 1.0
 * @since 2021/4/16 17:16
 **/
@Component
@Log4j2
public class DictManager implements InitializingBean {
    private static DictEntity NOT_NULL_DICT_ENTITY = new DictEntity("", Collections.EMPTY_LIST);
    private Map<String, DictEntity> enums;
    private Set<String> supportFieldNames;
    @Autowired
    private DictRepository dictRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        initEnums();

        initSupportFieldNames();
    }

    public DictEntity get(String name) {
        if(this.supportFieldNames.contains(name)) {
            return this.enums.get(name);
        }
        return NOT_NULL_DICT_ENTITY;
    }

    public Set<String> supportFieldNames() {
        return this.supportFieldNames;
    }

    private void initEnums() {
        Map<String, List<Dict>> dicts = loadDictFromDB();
        log.info(dicts);

        Map<String, DictEntity> dictItmes = Maps.newHashMap();
        for(Map.Entry<String, List<Dict>> e : dicts.entrySet()) {
            List<DictItem> items = e.getValue().stream().map(dict -> new DictItem(dict.getEnumValue(), dict.getEnumName(), dict.getEnumDesc())).collect(Collectors.toList());
            dictItmes.put(e.getKey(), new DictEntity(e.getKey(), items));
        }

        log.info(dictItmes);
        this.enums = dictItmes;

    }

    private Map<String, List<Dict>> loadDictFromDB() {
        return Lists.newArrayList(dictRepository.findAll()).stream().collect(Collectors.groupingBy(Dict::getType));
    }

    private void initSupportFieldNames() {
        this.supportFieldNames = Collections.unmodifiableSet(enums.keySet());
    }
}
