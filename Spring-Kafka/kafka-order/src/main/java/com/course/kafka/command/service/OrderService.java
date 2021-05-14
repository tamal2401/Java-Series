package com.course.kafka.command.service;

import com.course.kafka.api.request.OrderRequest;
import com.course.kafka.command.action.OrderAction;
import com.course.kafka.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderAction action;

    public String createOrder(OrderRequest request) {
        // convert to order entity
        Order order = action.convertToOrder(request);
        // save order entity to db
        action.saveToDb(order);
        // flatten the entity and send the entity to kafka
        order.getItems().forEach(action::publishToKafka);
        return "1";
    }
}
