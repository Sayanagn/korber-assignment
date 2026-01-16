package org.example.orderservice.controller;

import org.example.orderservice.dto.OrderRequest;
import org.example.orderservice.dto.OrderResponse;
import org.example.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add-orders")
    public OrderResponse placeOrder(@Valid @RequestBody OrderRequest request) {
        return orderService.addOrder(request);
    }
}
