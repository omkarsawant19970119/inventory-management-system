package com.omkar.inventory.common.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static com.omkar.inventory.common.kafka.constants.KafkaTopics.*;

@Configuration
@ConditionalOnProperty(name = "spring.kafka.bootstrap-servers")
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, Object> producerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");

        config.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);

        config.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);

        config.put(
                JsonSerializer.ADD_TYPE_INFO_HEADERS,
                false);

        // ========= PRODUCTION SETTINGS =========

        config.put(
                ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,
                true);

        config.put(
                ProducerConfig.ACKS_CONFIG,
                "all");

        config.put(
                ProducerConfig.RETRIES_CONFIG,
                Integer.MAX_VALUE);

        config.put(
                ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,
                5);

        config.put(
                ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG,
                120000);

        config.put(
                ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,
                30000);

        config.put(
                ProducerConfig.LINGER_MS_CONFIG,
                5);

        config.put(
                ProducerConfig.BATCH_SIZE_CONFIG,
                32768);

        config.put(
                ProducerConfig.COMPRESSION_TYPE_CONFIG,
                "snappy");

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {

        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic purchaseCreatedTopic() {
        return TopicBuilder.name(PURCHASE_CREATED)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic orderCreatedTopic() {
        return TopicBuilder.name(ORDER_CREATED)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic lowStockTopic() {
        return TopicBuilder.name(LOW_STOCK)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic UserCreatedTopic() {
        return TopicBuilder.name(USER_CREATED)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic productCreatedTopic() {
        return TopicBuilder.name(PRODUCT_CREATED)
                .partitions(3)
                .replicas(1)
                .build();
    }
}