package com.lqh.practice.sb.disruptor.demo.msg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> todo 写点注释吧
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/14 0014 14:15
 * @since 2021/6/14 0014 14:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Msg {
    private Long msgId;
    private Long msgSendTime;
    private String content;
}
