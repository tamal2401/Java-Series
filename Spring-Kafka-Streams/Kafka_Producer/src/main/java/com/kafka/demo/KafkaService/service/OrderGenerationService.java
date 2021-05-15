package com.kafka.demo.KafkaService.service;

import com.kafka.demo.KafkaService.model.OrderModel;

public interface OrderGenerationService {
    void saveAndPublishOrder(OrderModel model);
}
