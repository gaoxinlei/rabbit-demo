package com.example.rabbitmqdemo.controller;

import com.example.rabbitmqdemo.model.Notification;
import com.example.rabbitmqdemo.model.Response;
import com.example.rabbitmqdemo.sender.NotificationSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SendController {

    @Autowired
    private NotificationSender notificationSender;

    @PostMapping("/example/notify")
    @ResponseBody
    public Response send(String message){
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setId("2");
        notificationSender.send(notification);
        Response response = new Response();
        response.setContent("通知成功.");
        response.setId("2");
        return response;
    }

}
