package org.example.inventoryservice.service;

import org.example.inventoryservice.dto.InventoryResponse;
import org.example.inventoryservice.dto.InventoryResponseData;
import org.example.inventoryservice.entity.Inventory;

import org.example.inventoryservice.exception.InventoryNotFoundException;
import org.example.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<InventoryResponse> getInventoryByProductId(Long productId) {

        List<Inventory> inventoryList =  inventoryRepository.findByProductIdOrderByExpiryDateAsc(productId);
        if (inventoryList == null || inventoryList.isEmpty()) {
            throw new InventoryNotFoundException(productId);
        }

        return inventoryList.stream()
                .map(inventory -> {
                    InventoryResponseData data = new InventoryResponseData();
                    data.setBatchId(inventory.getBatchId());
                    data.setProductName(inventory.getProductName());
                    data.setQuantity(inventory.getQuantity());
                    data.setExpiryDate(inventory.getExpiryDate());

                    InventoryResponse response = new InventoryResponse();
                    response.setData(data);

                    return response;
                })
                .collect(Collectors.toList());
    }
}
