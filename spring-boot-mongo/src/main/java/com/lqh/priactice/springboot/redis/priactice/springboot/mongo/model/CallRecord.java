package com.lqh.priactice.springboot.redis.priactice.springboot.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * <p> 类描述: Model
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/06/07 17:41
 * @since 2020/06/07 17:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("call_records")
public class CallRecord {

    /**
     * 主键
     */
    @Id
    private String callId;

    /**
     * 通话ID，唯一确定一次通话
     */
    private String thirdCallId;

    /**
     * 主叫方
     */
    private Integer callType;

    /**
     * 被叫类型
     */
    private Integer peerType;

    /**
     * 主叫号码
     */
    private String callNo;

    /**
     * 被叫号码
     */
    private String peerNo;

    /**
     * 绑定ID （数据库返回的绑定表的ID）
     */
    private Long bindId;

    /**
     * 三方绑定ID
     */
    private String thirdBindId;

    /**
     * 业务ID
     */
    private String bizId;

    /**
     * 业务应用标识, oms/plat/ums
     */
    private String appKey;

    private Integer plat;

    /**
     * 城市ID
     */
    private Integer cityId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 省ID
     */
    private Integer provinceId;

    /**
     * 省名称
     */
    private String provinceName;

    /**
     * 隐私小号
     */
    private String xno;

    /**
     * 绑定的a号码
     */
    private String ano;

    /**
     * 绑定的b号码
     */
    private String bno;

    /**
     * 通话发生时间，主叫开始时间
     */
    private Date callTime;

    /**
     * 通话开始时间，被叫开始时间
     */
    private Date startTime;

    /**
     * 被叫响铃时间
     */
    private Date ringTime;

    /**
     * 通话结束时间，主、被叫结束时间一样
     */
    private Date finishTime;

    /**
     * 电话持续时长，单位秒:finish_time - call_time
     */
    private Integer callSustainDuration;

    /**
     * 通话时长，单位秒:finish_time - start_time
     */
    private Integer callDuration;

    /**
     * 响铃时长，单位秒:start_time - ring_time
     */
    private Integer ringDuration;

    /**
     * 结束发起方: 0: 平台结束； 1：主叫结束； 2：被叫结束；
     */
    private Integer finishType;

    /**
     * 结束状态（即挂断原因）： 0： 无绑定关系 1: 主叫挂机 2: 被叫挂机 3: 主叫放弃 4: 被叫无应答 5: 被叫忙 6: 被叫不可及 7: 路由失败 8: 中间号状态异常 9: 订单超过有效期 10: 平台系统异常 11: 关机 12: 停机 13: 拒接 14: 空号 注：11-14状态值只出现在AS呼叫
     */
    private Integer finishState;

    /**
     * 通话状态  1 呼出：电话已呼出 ，2 呼通：被叫手机振铃 ，3 接听：被叫已接听电话
     */
    private Integer callStatus;

    /**
     * 响铃时长是否是系统默认的
     */
    private Boolean systemRing;

    /**
     * 是否接听 0未接听 1已接听
     */
    private Integer callStart;

    /**
     * 录音地址
     */
    private String tapeUrl;

    /**
     * 创建人
     */
    private String creater;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
