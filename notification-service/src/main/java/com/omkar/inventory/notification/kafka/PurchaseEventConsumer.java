package com.omkar.inventory.notification.kafka;

import com.omkar.inventory.common.kafka.events.PurchaseCreatedEvent;
import com.omkar.inventory.notification.dto.NotificationRequest;
import com.omkar.inventory.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PurchaseEventConsumer {

    private final NotificationService notificationService;

    @RetryableTopic(
            attempts = "3",

            backoff = @Backoff(
                    delay = 1000,
                    multiplier = 2.0
            ),

            dltTopicSuffix = "-dlt",

            autoCreateTopics = "true"
    )
    @KafkaListener(
            topics = "purchase-created",
            groupId = "notification-group"
    )
    public void consume(PurchaseCreatedEvent event) {

        log.info("Purchase Event Received : {}", event);

        NotificationRequest request = new NotificationRequest();

        request.setRecipient("Warehouse Manager");
        request.setSubject("New Purchase Received");

        request.setMessage(
                "Purchase Id : " + event.getPurchaseId() +
                        ", Product Id : " + event.getProductId() +
                        ", Quantity : " + event.getQuantity());

        request.setType("EMAIL");

        notificationService.createNotification(request);

        log.info("Notification Created Successfully");
    }
}