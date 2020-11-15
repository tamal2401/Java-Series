package com.demo.dequer;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.invoke.MethodHandles;

public class QueueListener {

    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private Gson gson;

    @KafkaListener(topics = "new_product_alert")
    public void queueListener(String message) {
        if (null != message && !StringUtils.isBlank(message)) {
            Product alertProd = gson.fromJson(message, Product.class);
            log.info("New Product details added :: {}", alertProd.toString());
        }
    }
}
