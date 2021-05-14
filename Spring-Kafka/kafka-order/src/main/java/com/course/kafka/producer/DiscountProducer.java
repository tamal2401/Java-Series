package com.course.kafka.producer;

import com.course.kafka.broker.message.DiscountMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.ExecutionException;

@Service
public class DiscountProducer {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private KafkaTemplate<String, Object> template;

    public void send(DiscountMessage msg){
        try {
            SendResult<String, Object> result = template.send("t.comodity.promotion", msg).get();
            log.info("Discount message published successfully, Message: {}", result.getProducerRecord().value());
        } catch (InterruptedException| ExecutionException e) {
            log.warn("Error publishing discount message: {}, cause: {}", msg, e.getCause());
        }
    }
}
