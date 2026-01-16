package org.example.orderservice.exception;

public class InventoryNotFoundException extends RuntimeException {
    private final Long productId;

    public InventoryNotFoundException(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
