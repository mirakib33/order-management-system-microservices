package com.bs23.inventoryservice.controller;

import com.bs23.inventoryservice.entity.Inventory;
import com.bs23.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{productId}")
    public Inventory getInventory(@PathVariable Long productId) {
        return inventoryService.getInventory(productId);
    }
}
