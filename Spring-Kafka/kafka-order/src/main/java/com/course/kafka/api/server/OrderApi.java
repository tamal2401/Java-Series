package com.course.kafka.api.server;

import com.course.kafka.api.request.OrderRequest;
import com.course.kafka.api.response.OrderResponse;
import com.course.kafka.command.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderApi {

    @Autowired
    OrderService service;

    @GetMapping(value = "/api/order/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderResponse createOrder(OrderRequest request) {
        String orderNum = service.createOrder(request);

        var response = new OrderResponse(orderNum);

        return response;
    }
}
