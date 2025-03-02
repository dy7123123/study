package com.dy.study.pojo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ActivityRule {
    private Long id;
    private String activityId; //活动ID
    private String ruleCode; // 邀请人 or 受邀人规则编码
    private String rule;     // 具体规则
    private String ext;  // 扩展字段
    private String creator; // 创建人
    private String modifier;// 修改人
    private Integer yn;// 有效标记
    private LocalDateTime createdTime; // 创建时间
    private LocalDateTime modifiedTime; // 修改时间
}
