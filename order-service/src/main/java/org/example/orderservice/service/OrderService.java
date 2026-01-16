package org.example.orderservice.service;

import org.example.orderservice.dto.InventoryResponse;
import org.example.orderservice.dto.OrderRequest;
import org.example.orderservice.dto.OrderResponse;
import org.example.orderservice.entity.OrderEntity;
import org.example.orderservice.exception.InventoryNotFoundException;
import org.example.orderservice.exception.OrderException;
import org.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryServiceClient inventoryServiceClient;

    public OrderService(OrderRepository orderRepository, InventoryServiceClient inventoryServiceClient) {
        this.orderRepository = orderRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }



    public OrderResponse addOrder(OrderRequest request) {
        if (request.getQuantity() <= 0) {
            throw new OrderException("Order quantity must be greater than zero");
        }

        InventoryResponse[] inventory = inventoryServiceClient.getInventoryDetails(request.getProductId());

        if (inventory == null) {
            throw new InventoryNotFoundException(request.getProductId());
        }


        OrderEntity order = new OrderEntity();
        order.setProductId(request.getProductId());
        order.setProductName(inventory[0].getData().getProductName());
        order.setQuantity(request.getQuantity());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());

        OrderEntity saved = orderRepository.save(order);

        OrderResponse response = new OrderResponse();
        response.setOrderId(saved.getOrderId());
        response.setProductId(saved.getProductId());
        response.setProductName(saved.getProductName());
        response.setQuantity(saved.getQuantity());
        response.setStatus(saved.getStatus());
        response.setMessage("Order placed. Inventory reserved.");

        return response;
    }

}
