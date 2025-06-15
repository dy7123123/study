package com.dy.study.mapper;

import com.dy.study.dto.UserOrderBehavior;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserOrderBehaviorMapper {

    @Insert("INSERT INTO user_order_behavior(pin, order_id, vender_id, order_completed_time, raw_message) " +
            "VALUES(#{pin}, #{orderId}, #{venderId}, #{orderCompletedTime}, #{rawMessage})")
    void insert(UserOrderBehavior behavior);
}
