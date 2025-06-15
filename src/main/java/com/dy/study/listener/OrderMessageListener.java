package com.dy.study.listener;

import com.dy.study.dto.UserOrderBehavior;
import com.dy.study.mapper.UserOrderBehaviorMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@Slf4j

public class OrderMessageListener {

    private final UserOrderBehaviorMapper behaviorMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OrderMessageListener(UserOrderBehaviorMapper behaviorMapper) {
        this.behaviorMapper = behaviorMapper;
    }

    @RabbitListener(queues = "order.complete.queue")
    public void receive(String message) {
        log.info("接收到 RabbitMQ 消息：{}", message);
        try {
            Map<String, Object> map = objectMapper.readValue(message, Map.class);

            UserOrderBehavior behavior = new UserOrderBehavior();
            behavior.setPin(Long.valueOf(map.get("pin").toString()));
            behavior.setOrderId(map.get("orderId").toString());
            behavior.setVenderId(Long.valueOf(map.get("venderId").toString()));
            behavior.setOrderCompletedTime(new Date(Long.parseLong(map.get("orderCompletedTime").toString())));
            behavior.setRawMessage(message);

            behaviorMapper.insert(behavior);
            log.info("入库成功：orderId={}", behavior.getOrderId());
        } catch (Exception e) {
            log.error("解析消息失败", e);
        }
    }
}
