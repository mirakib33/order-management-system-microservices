package com.bs23.orderservice.service;

import com.bs23.orderservice.entity.Order;
import com.bs23.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final String ORDER_CREATED_TOPIC = "order.created";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        kafkaTemplate.send(ORDER_CREATED_TOPIC, "Order Created: " + savedOrder.getId());
        return savedOrder;
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}

