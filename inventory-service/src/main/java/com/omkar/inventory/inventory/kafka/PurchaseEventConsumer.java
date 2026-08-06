package com.omkar.inventory.inventory.kafka;

import com.omkar.inventory.common.kafka.events.PurchaseCreatedEvent;
import com.omkar.inventory.inventory.service.EventProcessingService;
import com.omkar.inventory.inventory.service.InventoryService;
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

    private final InventoryService inventoryService;

    private final EventProcessingService eventProcessingService;

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
            groupId = "inventory-group"
    )
    public void consume(PurchaseCreatedEvent event){

        log.info("Inventory Event Received {}",event);

        inventoryService.addStockFromPurchase(
                event.getProductId(),
                event.getQuantity());

        eventProcessingService
                .markProcessed(event.getEventId());

    }

}