package com.course.kafka.kafka.listener;

import com.course.kafka.kafka.broker.message.OrderMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class ComodityOrderListener {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @KafkaListener(topics = "t.comodity.order")
    private void listen(OrderMessage msg) {
        log.info("processing order: {}, item: {}, credit card number: {}, total amount: {}",
                msg.getOrderNumber(),
                msg.getItemName(),
                msg.getCreditCardNumber(),
                msg.getQuantity() * msg.getPrice());
    }
}
