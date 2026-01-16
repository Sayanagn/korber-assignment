package org.example.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryResponseData {

    private Long batchId;
    private String productName;
    private Integer quantity;
    private String expiryDate;
}
