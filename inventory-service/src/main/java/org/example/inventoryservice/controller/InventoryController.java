package org.example.inventoryservice.controller;


import org.example.inventoryservice.dto.InventoryResponse;
import org.example.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/get-inventory-details")
    public List<InventoryResponse> getInventory(@RequestParam(required = true) Long productId) {
        return inventoryService.getInventoryByProductId(productId);
    }
}
