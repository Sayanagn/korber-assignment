package org.example.inventoryservice.controller;


import org.example.inventoryservice.dto.InventoryResponse;
import org.example.inventoryservice.entity.Inventory;
import org.example.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productId}")
    public List<InventoryResponse> getInventory(@PathVariable Long productId) {
        return inventoryService.getInventoryByProductId(productId);
    }
}
