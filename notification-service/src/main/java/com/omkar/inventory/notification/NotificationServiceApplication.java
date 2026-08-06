package com.omkar.inventory.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaRetryTopic;

@SpringBootApplication(scanBasePackages = {
        "com.omkar.inventory.notification",
        "com.omkar.inventory.common"})
@EnableKafka
@EnableKafkaRetryTopic
public class NotificationServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(NotificationServiceApplication.class, args);
    }
}