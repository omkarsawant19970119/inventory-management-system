package com.omkar.inventory.inventory.kafka;

import com.omkar.inventory.common.kafka.events.PurchaseCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PurchaseDeadLetterConsumer {

    @KafkaListener(
            topics = "purchase-created-dlt",
            groupId = "inventory-dlt-group"
    )
    public void consume(PurchaseCreatedEvent event){

        log.error("====================================");
        log.error("MESSAGE MOVED TO DEAD LETTER QUEUE");
        log.error("Purchase Id : {}",event.getPurchaseId());
        log.error("Product Id : {}",event.getProductId());
        log.error("Quantity : {}",event.getQuantity());
        log.error("====================================");

    }

}