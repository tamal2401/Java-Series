package com.kafka.demo.KafkaService.controller;

import com.kafka.demo.KafkaService.model.OrderModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProducerController {

    @PostMapping(value = "/api/v1/create/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void publishOrder(@RequestBody @Valid OrderModel reqModel){


    }
}
