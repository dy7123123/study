package com.dy.study.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityAward {
    private Long id;
    private String activityId; // 活动 ID
    private Integer awardType; // 奖励类型 1、优惠券 2、红包
    private String refId; // 奖项 id 优惠key
    private String creator; // 创建人
    private String modifier; // 修改人
    private Integer yn;     // 有效标记
    private LocalDateTime createdTime;  // 创建时间
    private LocalDateTime modifiedTime; // 修改时间
}
