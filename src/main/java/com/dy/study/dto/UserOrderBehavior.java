package com.dy.study.dto;

import lombok.Data;
import java.util.Date;

@Data
public class UserOrderBehavior {
    private Long id;
    private Long pin;
    private String orderId;
    private Long venderId;
    private Date orderCompletedTime;
    private Date createTime;
    private String rawMessage;
}
