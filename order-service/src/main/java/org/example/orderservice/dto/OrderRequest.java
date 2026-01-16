package org.example.orderservice.dto;

import org.jetbrains.annotations.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    @NotNull
    private Long productId;

    @Positive
    private Integer quantity;
}

