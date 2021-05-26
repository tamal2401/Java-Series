package com.kafka.demo.order.generation.service;

import com.kafka.demo.order.generation.model.OrderModel;
import com.kafka.demo.order.generation.model.RequestModel;

public interface OrderGenerationService {
    OrderModel saveAndPublishOrder(RequestModel model);
}
