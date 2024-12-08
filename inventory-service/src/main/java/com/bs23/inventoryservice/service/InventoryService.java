package com.bs23.inventoryservice.service;

import com.bs23.inventoryservice.entity.Inventory;
import com.bs23.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @KafkaListener(topics = "order.created", groupId = "inventory_group")
    public void handleOrderCreated(String message) {
        // Logic to handle order event
        // Update inventory based on order created
        System.out.println("Order received: " + message);
    }

    public Inventory getInventory(Long productId) {
        return inventoryRepository.findById(productId).orElseThrow(() -> new RuntimeException("Inventory not found"));
    }
}
