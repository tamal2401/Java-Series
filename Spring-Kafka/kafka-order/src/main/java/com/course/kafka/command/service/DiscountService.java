package com.course.kafka.command.service;

import com.course.kafka.api.request.DiscountRequest;
import com.course.kafka.command.action.DiscountAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    @Autowired
    DiscountAction action;

    public void createDiscount(DiscountRequest request) {
        // convert to order entity
        action.publishtoDiscountQueue(request);
    }
}
