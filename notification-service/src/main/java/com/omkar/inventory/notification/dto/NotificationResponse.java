package com.omkar.inventory.notification.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponse {

    private Long id;

    private String recipient;

    private String subject;

    private String message;

    private String type;

    private String status;

    private LocalDateTime createdAt;
}