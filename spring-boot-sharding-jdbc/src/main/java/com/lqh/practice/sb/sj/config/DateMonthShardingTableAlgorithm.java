package com.lqh.practice.sb.sj.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;


/**
* 按月分片算法
* @author LuJia
* @version v1.0
* @since 2021/3/8 9:18
**/
public class DateMonthShardingTableAlgorithm implements PreciseShardingAlgorithm<Date>{
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
        Date createTime = shardingValue.getValue();
        int code = Integer.parseInt(new SimpleDateFormat("MM").format(createTime)) - 1;

//                Integer.parseInt(obj.toString().substring(5, 7)) - 1;
        for (Object each : availableTargetNames) {
            if (each.toString().endsWith(code + "")) {
                return (String) each;
            }
        }
        throw new UnsupportedOperationException();
    }
}
