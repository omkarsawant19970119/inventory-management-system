package com.omkar.inventory.notification.repository;

import com.omkar.inventory.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}