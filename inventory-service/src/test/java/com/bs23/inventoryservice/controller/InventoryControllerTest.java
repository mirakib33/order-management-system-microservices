package com.bs23.inventoryservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetInventory() throws Exception {
        mockMvc.perform(get("/inventory/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(10));
    }

    @Test
    void testGetInventoryNotFound() throws Exception {
        mockMvc.perform(get("/inventory/999"))
                .andExpect(status().isNotFound());
    }
}
