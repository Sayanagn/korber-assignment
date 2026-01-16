package org.example.orderservice.service;


import org.example.orderservice.dto.InventoryResponse;
import org.example.orderservice.dto.InventoryResponseData;
import org.example.orderservice.dto.OrderRequest;
import org.example.orderservice.dto.OrderResponse;
import org.example.orderservice.entity.OrderEntity;
import org.example.orderservice.exception.InventoryNotFoundException;
import org.example.orderservice.exception.OrderException;
import org.example.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private InventoryServiceClient inventoryServiceClient;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;


    @Test
    void testOrderException() {

        OrderRequest request = new OrderRequest();
        request.setProductId(1001L);
        request.setQuantity(0);

        OrderException exception = assertThrows(OrderException.class, () -> orderService.addOrder(request));

        assertEquals("Order quantity must be greater than zero", exception.getMessage());

        verifyNoInteractions(inventoryServiceClient);
        verifyNoInteractions(orderRepository);
    }

    @Test
    void testNotFoundExceptionWhenInventoryIsNull() {

        OrderRequest request = new OrderRequest();
        request.setProductId(999L);
        request.setQuantity(2);

        when(inventoryServiceClient.getInventoryDetails(999L))
                .thenReturn(null);

        InventoryNotFoundException exception = assertThrows(InventoryNotFoundException.class, () -> orderService.addOrder(request));

        assertEquals(999L, exception.getProductId());
        verify(orderRepository, never()).save(any());
    }


    @Test
    void shouldPlaceOrderSuccessfully() {
        OrderRequest request = new OrderRequest();
        request.setProductId(1001L);
        request.setQuantity(2);

        InventoryResponseData data = new InventoryResponseData();
        data.setProductName("tablet");

        InventoryResponse response = new InventoryResponse();
        response.setData(data);

        when(inventoryServiceClient.getInventoryDetails(1001L))
                .thenReturn(new InventoryResponse[]{response});

        OrderEntity savedOrder = new OrderEntity();
        savedOrder.setOrderId(1L);

        when(orderRepository.save(any(OrderEntity.class))).thenReturn(savedOrder);


        OrderResponse result = orderService.addOrder(request);

        assertNotNull(result);
        verify(orderRepository).save(any());
    }
}
