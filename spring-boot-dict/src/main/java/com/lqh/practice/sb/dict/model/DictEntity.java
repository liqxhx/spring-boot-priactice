package com.lqh.practice.sb.dict.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * DictEntity 枚举实体类
 * @author liqh
 * @version 1.0
 * @since 2021/4/16 17:00
 **/
public class DictEntity {
    /**
     * 字典表的 categ_two_type
     */
    private final String fieldName;

    private List<DictItem> items = Collections.EMPTY_LIST;

    private Map<Integer, String> codeMap = Collections.EMPTY_MAP;

    private Map<String, Integer> nameMap = Collections.EMPTY_MAP;

    public DictEntity(String fieldName, List<DictItem> items) {
        this.fieldName = fieldName;
        init(items);
    }

    private void init(List<DictItem> items) {
        if(items == null || items.isEmpty()) {
            return;
        }
        this.items = Collections.unmodifiableList(items);
        this.codeMap = Collections.unmodifiableMap(items.stream().collect(Collectors.toMap(DictItem::getEnumValue, DictItem::getEnumName)));
        this.nameMap = Collections.unmodifiableMap(items.stream().collect(Collectors.toMap(DictItem::getEnumName, DictItem::getEnumValue)));
    }

    /**
     *
     * @return {@link List<DictItem>}
     */
    public List<DictItem> enumItems() {
        return Collections.unmodifiableList(this.items);
    }

    /**
     * 值对应的名称
     * @return {@link Map<Integer, String>}
     */
    public Map<Integer, String> codeMap() {
        return this.codeMap;
    }

    /**
     * 名称对应的值
     * @return {@link Map<String, Integer>}
     */
    public Map<String, Integer> nameMap() {
        return this.nameMap;
    }

    /**
     * 根据code查询检举
     * @param code 检举值
     * @return {@link DictItem}
     */
    public DictItem of(Integer code) {
        if(code == null) {
            return DictItem.empty();
        }
        return items.stream().filter(item -> item.getEnumValue() == code.intValue()).findAny().orElseGet(()-> DictItem.empty());
    }

    /**
     * 根据枚举名获取枚举
     * @param name 枚举名
     * @return {@link DictItem}
     */
    public DictItem of(String name) {
        if(name == null || name.trim().length() == 0) {
            return DictItem.empty();
        }

        return items.stream().filter(item -> item.getEnumName().equalsIgnoreCase(name)).findAny().orElseGet(()-> DictItem.empty());
    }
}
