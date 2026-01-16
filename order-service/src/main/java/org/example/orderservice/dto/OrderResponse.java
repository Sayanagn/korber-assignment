package org.example.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {

    private Long orderId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private String status;
    private String message;
}
