package com.kafka.demo.order.generation.dao;

import com.kafka.demo.order.generation.entity.OrderReceivedEntity;
import com.kafka.demo.order.generation.model.OrderModel;

public interface OrderArchivalDao {

    OrderReceivedEntity saveOrder(OrderModel model);
}
