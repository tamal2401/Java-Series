package com.course.kafka.api.server;

import com.course.kafka.api.request.DiscountRequest;
import com.course.kafka.command.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class DiscountApi {

    @Autowired
    DiscountService service;

    @PostMapping(value = "/api/promotion/discount", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createPromotion(@RequestBody DiscountRequest request) {
        service.createDiscount(request);

        return String.format("discount code: {} with discount percentage: {}", request.getDiscountCode(), request.getDiscountPercentage());
    }
}
