package com.example.rabbitmqdemo.receiver;

import com.example.rabbitmqdemo.model.Notification;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;

public class NotificationReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationReceiver.class);

    @RabbitListener(queues = "feedback-1")
    @RabbitHandler
    public void receive(Notification notification, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag){
        LOGGER.info("通知收到:{}",notification);
        try {
            //随机决定确认成功还是失败.
            int number = (int)(Math.random()*1000);
            LOGGER.info("随机因子:{}",number);
            if(number%10==1) {
                channel.basicAck(tag, false);
                LOGGER.info("消费者:确认消息");
            }else{
                channel.basicNack(tag,false,true);
                LOGGER.info("消费者:拒绝消息");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        throw new RuntimeException("试试重发");
    }
}
