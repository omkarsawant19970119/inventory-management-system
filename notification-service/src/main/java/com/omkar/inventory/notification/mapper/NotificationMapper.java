package com.omkar.inventory.notification.mapper;

import com.omkar.inventory.notification.dto.NotificationRequest;
import com.omkar.inventory.notification.dto.NotificationResponse;
import com.omkar.inventory.notification.entity.Notification;

import java.time.LocalDateTime;

public class NotificationMapper {

    public static Notification toEntity(NotificationRequest request){

        return Notification.builder()
                .recipient(request.getRecipient())
                .subject(request.getSubject())
                .message(request.getMessage())
                .type(request.getType())
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static NotificationResponse toResponse(Notification notification){

        return NotificationResponse.builder()
                .id(notification.getId())
                .recipient(notification.getRecipient())
                .subject(notification.getSubject())
                .message(notification.getMessage())
                .type(notification.getType())
                .status(notification.getStatus())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}