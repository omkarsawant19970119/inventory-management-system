package com.omkar.inventory.notification.dto;

import lombok.Data;

@Data
public class NotificationRequest {

    private String recipient;

    private String subject;

    private String message;

    private String type;
}