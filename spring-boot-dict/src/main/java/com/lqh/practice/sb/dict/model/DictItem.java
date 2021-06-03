package com.lqh.practice.sb.dict.model;

/**
 * EnumItem 枚举项
 * @author liqh
 * @version 1.0
 * @since 2021/4/16 17:00
 **/
public class DictItem {
    private final int enumValue;
    private final String enumName;
    private final String enumDesc;

    public static DictItem empty() {
        return new DictItem(-1, "未知", "未知");
    }

    public DictItem(int enumValue, String enumName, String enumDesc) {
        this.enumDesc = enumDesc;
        this.enumName = enumName;
        this.enumValue = enumValue;
    }

    public int getEnumValue() {
        return enumValue;
    }

    public String getEnumName() {
        return enumName;
    }

    public String getEnumDesc() {
        return enumDesc;
    }
}

