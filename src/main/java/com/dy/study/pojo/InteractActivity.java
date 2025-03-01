package com.dy.study.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteractActivity {
    private Long id;  //ID
    private String activityId; //活动ID，基于活动ID分表
    private String activityName; //活动名称
    private Integer activityType; //活动类型 1、邀请有礼
    private Date startTime; //活动开始时间
    private Date endTime; //活动结束时间
    private Integer status; //活动状态 1、新建 2、有效 3、进行中 4、结束 5、中止
    private String activityTerms; //活动说明
    private String ext; //扩展字段
    private String creator; //创建人PIN
    private String modifier; //修改人PIN
    private Integer yn; //有效标记，有效状态
    private Date createdTime; //创建时间
    private Date modifiedTime; //修改时间
}
