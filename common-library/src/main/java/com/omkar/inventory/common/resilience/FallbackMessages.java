package com.omkar.inventory.common.resilience;

public final class FallbackMessages {

    private FallbackMessages() {
    }

    public static final String PRODUCT_SERVICE_DOWN =
            "Product Service is temporarily unavailable.";

    public static final String INVENTORY_SERVICE_DOWN =
            "Inventory Service is temporarily unavailable.";

    public static final String ORDER_SERVICE_DOWN =
            "Order Service is temporarily unavailable.";

    public static final String PURCHASE_SERVICE_DOWN =
            "Purchase Service is temporarily unavailable.";

}