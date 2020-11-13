package com.demo.dashboard.controller;

import com.demo.dashboard.domain.Product;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;

@RestController
public class EventProducerController {

    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Gson jsonConverter;

    @PostMapping(value = "/api/publish/{topic}")
    public void publishMessage(@PathVariable String topic, @RequestBody Product prod) {
        if (!StringUtils.isEmpty(prod)) {
            ListenableFuture<SendResult<String, String>> futureRes = kafkaTemplate.send(topic, jsonConverter.toJson(prod));
            futureRes.addCallback(
                    success -> {
                        log.info("Event prpoduced with Messege : {}", success);
                    },
                    failure -> {
                        log.info("Faliure occured while publishing message : {}", failure);
                    }
            );
        }
    }
}
