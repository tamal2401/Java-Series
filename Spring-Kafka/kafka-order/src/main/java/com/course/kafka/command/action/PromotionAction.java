package com.course.kafka.command.action;

import com.course.kafka.api.request.PromotionRequest;
import com.course.kafka.broker.message.PromotionMessage;
import com.course.kafka.producer.PromotionProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PromotionAction {

    @Autowired
    PromotionProducer producer;

    public void publishToPromotionQueue(PromotionRequest request) {
        producer.send(new PromotionMessage(request.getPromotionCode()));
    }
}
