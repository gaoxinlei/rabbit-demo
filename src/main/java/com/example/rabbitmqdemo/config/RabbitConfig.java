package com.example.rabbitmqdemo.config;

import com.example.rabbitmqdemo.receiver.NotificationReceiver;
import com.example.rabbitmqdemo.sender.NotificationSender;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    //发送者
    @Bean
    public NotificationSender notificationSender(){
        return new NotificationSender();
    }

    //接收者
    @Bean
    public NotificationReceiver notificationReceiver(){
        return new NotificationReceiver();
    }

    //队列
    @Bean
    public Queue queue(){
        return new Queue("feedback-1",true,false,false);
    }
}
