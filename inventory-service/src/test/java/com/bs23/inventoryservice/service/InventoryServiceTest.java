package com.bs23.inventoryservice.service;

import com.bs23.inventoryservice.entity.Inventory;
import com.bs23.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inventory = new Inventory(1L, 101L, 10);
    }

    @Test
    void testGetInventory() {
        when(inventoryRepository.findById(1L)).thenReturn(java.util.Optional.of(inventory));

        Inventory fetchedInventory = inventoryService.getInventory(1L);

        assertNotNull(fetchedInventory);
        assertEquals(10, fetchedInventory.getQuantity());

        verify(inventoryRepository).findById(1L);
    }

    @Test
    void testGetInventoryNotFound() {
        when(inventoryRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            inventoryService.getInventory(1L);
        });

        assertEquals("Inventory not found", exception.getMessage());
    }
}
