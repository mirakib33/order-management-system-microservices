package com.bs23.orderservice.service;

import com.bs23.orderservice.entity.Order;
import com.bs23.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private OrderService orderService;

    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order();
        order.setId(1L);
        order.setProductId(101L);
        order.setQuantity(2);
        order.setStatus("Created");
    }

    @Test
    void testCreateOrder() {
        // Mock the behavior of the repository
        when(orderRepository.save(order)).thenReturn(order);

        // Call the service method
        Order createdOrder = orderService.createOrder(order);

        // Verify interactions
        verify(orderRepository).save(order);
        verify(kafkaTemplate).send("order.created", "Order Created: 1");

        // Assert the results
        assertNotNull(createdOrder);
        assertEquals(1L, createdOrder.getId());
        assertEquals("Created", createdOrder.getStatus());
    }

    @Test
    void testGetOrder() {
        // Mock the behavior of the repository
        when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));

        // Call the service method
        Order foundOrder = orderService.getOrder(1L);

        // Verify interactions
        verify(orderRepository).findById(1L);

        // Assert the results
        assertNotNull(foundOrder);
        assertEquals(1L, foundOrder.getId());
    }

    @Test
    void testGetOrderNotFound() {
        // Mock the behavior of the repository
        when(orderRepository.findById(2L)).thenReturn(java.util.Optional.empty());

        // Call the service method and verify the exception
        assertThrows(RuntimeException.class, () -> orderService.getOrder(2L));
    }
}
