package com.course.kafka.listener;

import com.course.kafka.broker.message.PromotionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class PromotionUppercaseListener {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @KafkaListener(topics = "t.comodity.promotion-uppercase")
    public void listen(PromotionMessage msg){
        log.info("processing upper case promotioncode: {}", msg);
    }
}
