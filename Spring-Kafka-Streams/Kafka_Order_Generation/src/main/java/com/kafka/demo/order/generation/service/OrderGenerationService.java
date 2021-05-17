package com.kafka.demo.order.generation.service;

import com.kafka.demo.order.generation.model.OrderModel;

public interface OrderGenerationService {
    void saveAndPublishOrder(OrderModel model);
}
