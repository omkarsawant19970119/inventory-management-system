package com.omkar.inventory.common.kafka.constants;

public final class KafkaTopics {

    private KafkaTopics(){}

    public static final String PURCHASE_CREATED =
            "purchase-created";

    public static final String ORDER_CREATED =
            "order-created";

    public static final String PRODUCT_CREATED =
            "product-created";

    public static final String USER_CREATED =
            "user-created";

    public static final String LOW_STOCK =
            "low-stock";
}