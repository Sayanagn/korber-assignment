package org.example.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class InventoryResponseData {
    private Long batchId;
    private String productName;
    private Integer quantity;
    private LocalDate expiryDate;


}