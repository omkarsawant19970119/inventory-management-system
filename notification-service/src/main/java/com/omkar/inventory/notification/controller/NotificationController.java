package com.omkar.inventory.notification.controller;

import com.omkar.inventory.notification.dto.NotificationRequest;
import com.omkar.inventory.notification.dto.NotificationResponse;
import com.omkar.inventory.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotificationResponse create(
            @RequestBody NotificationRequest request) {

        return service.createNotification(request);
    }

    @GetMapping("/{id}")
    public NotificationResponse getById(
            @PathVariable Long id) {

        return service.getNotificationById(id);
    }

    @GetMapping
    public List<NotificationResponse> getAll() {

        return service.getAllNotifications();
    }

    @PutMapping("/{id}")
    public NotificationResponse update(
            @PathVariable Long id,
            @RequestBody NotificationRequest request) {

        return service.updateNotification(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id) {

        service.deleteNotification(id);
    }
}