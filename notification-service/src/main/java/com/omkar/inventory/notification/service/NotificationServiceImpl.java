package com.omkar.inventory.notification.service;

import com.omkar.inventory.notification.dto.NotificationRequest;
import com.omkar.inventory.notification.dto.NotificationResponse;
import com.omkar.inventory.notification.entity.Notification;
import com.omkar.inventory.notification.mapper.NotificationMapper;
import com.omkar.inventory.notification.repository.NotificationRepository;
import com.omkar.inventory.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;

    @Override
    public NotificationResponse createNotification(NotificationRequest request) {

        Notification notification = NotificationMapper.toEntity(request);

        Notification saved = repository.save(notification);

        return NotificationMapper.toResponse(saved);
    }

    @Override
    public NotificationResponse getNotificationById(Long id) {

        Notification notification = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Notification not found with id : " + id));

        return NotificationMapper.toResponse(notification);
    }

    @Override
    public List<NotificationResponse> getAllNotifications() {

        return repository.findAll()
                .stream()
                .map(NotificationMapper::toResponse)
                .toList();
    }

    @Override
    public NotificationResponse updateNotification(Long id,
                                                   NotificationRequest request) {

        Notification notification = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Notification not found with id : " + id));

        notification.setRecipient(request.getRecipient());
        notification.setSubject(request.getSubject());
        notification.setMessage(request.getMessage());
        notification.setType(request.getType());

        Notification updated = repository.save(notification);

        return NotificationMapper.toResponse(updated);
    }

    @Override
    public void deleteNotification(Long id) {

        Notification notification = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Notification not found with id : " + id));

        repository.delete(notification);
    }
}