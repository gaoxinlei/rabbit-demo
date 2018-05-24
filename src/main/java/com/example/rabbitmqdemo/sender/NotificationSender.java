package com.example.rabbitmqdemo.sender;

import com.example.rabbitmqdemo.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;

public class NotificationSender implements  RabbitTemplate.ConfirmCallback{

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationSender.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void send(Notification notification){
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("测试通知唯一标识");
        rabbitTemplate.convertAndSend("feedback-1",notification);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        LOGGER.info("确认接收状态:{}",ack);
        LOGGER.info("标识:{}",correlationData);
        LOGGER.info("原因:{}",cause);
    }
}
