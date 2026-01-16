package org.example.inventoryservice.exception;


import lombok.Getter;

@Getter
public class InventoryNotFoundException extends RuntimeException {

    private final Long productId;

    public InventoryNotFoundException(Long productId) {
        this.productId = productId;
    }

}
