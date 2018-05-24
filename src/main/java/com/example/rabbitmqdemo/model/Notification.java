package com.example.rabbitmqdemo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Notification implements Serializable {
    private String id;
    private String message;
}
