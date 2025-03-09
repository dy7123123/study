package com.dy.study.dto;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityExportDTO {
    private String activityName;
    private Date startTime;
    private Date endTime;
    private Integer status;
    private Integer inviteSuccessCount;
    private String creator;
    private Date createdTime;
}
