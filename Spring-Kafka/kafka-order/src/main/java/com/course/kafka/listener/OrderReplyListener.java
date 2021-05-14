package com.course.kafka.listener;

import com.course.kafka.broker.message.OrderReplyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class OrderReplyListener {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @KafkaListener(topics = "t.comodity.order-reply")
    public void listen(OrderReplyMessage msg) {
        log.info("Order reply message is: {}", msg);
    }
}
