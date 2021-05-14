package com.course.kafka.command.action;

import com.course.kafka.api.request.DiscountRequest;
import com.course.kafka.broker.message.DiscountMessage;
import com.course.kafka.entity.Order;
import com.course.kafka.producer.DiscountProducer;
import org.springframework.beans.factory.annotation.Autowired;

public class DiscountAction {

    @Autowired
    DiscountProducer producer;

    public void publishtoDiscountQueue(DiscountRequest request) {
        producer.send(new DiscountMessage(request.getDiscountCode(), request.getDiscountPercentage()));
    }
}
