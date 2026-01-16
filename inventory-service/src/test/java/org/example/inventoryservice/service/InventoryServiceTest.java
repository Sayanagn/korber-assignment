package org.example.inventoryservice.service;

import org.example.inventoryservice.dto.InventoryResponse;
import org.example.inventoryservice.dto.InventoryResponseData;
import org.example.inventoryservice.entity.Inventory;
import org.example.inventoryservice.exception.InventoryNotFoundException;
import org.example.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void shouldReturnInventoryWhenFound() {

        Inventory inventory = new Inventory();
        inventory.setBatchId(1L);
        inventory.setProductId(101L);
        inventory.setProductName("Laptop");
        inventory.setQuantity(10);
        inventory.setExpiryDate(LocalDate.now().plusDays(30));

        when(inventoryRepository.findByProductIdOrderByExpiryDateAsc(101L))
                .thenReturn(List.of(inventory));


        List<InventoryResponse> result =
                inventoryService.getInventoryByProductId(101L);

        assertNotNull(result);
        assertEquals(1, result.size());

        InventoryResponseData data = result.get(0).getData();
        assertEquals(1L, data.getBatchId());
        assertEquals("Laptop", data.getProductName());
        assertEquals(10, data.getQuantity());
    }

    @Test
    void shouldThrowExceptionWhenInventoryNotFound() {

        when(inventoryRepository.findByProductIdOrderByExpiryDateAsc(101L))
                .thenReturn(List.of());

        assertThrows(
                InventoryNotFoundException.class,
                () -> inventoryService.getInventoryByProductId(101L)
        );
    }

    @Test
    void shouldThrowExceptionWhenInventoryNotFound_null() {

        when(inventoryRepository.findByProductIdOrderByExpiryDateAsc(101L))
                .thenReturn(null);

        assertThrows(
                InventoryNotFoundException.class,
                () -> inventoryService.getInventoryByProductId(101L)
        );
    }
}
