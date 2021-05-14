package com.course.kafka.listener;

import com.course.kafka.broker.message.DiscountMessage;
import com.course.kafka.broker.message.PromotionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
@KafkaListener(topics = "t.comodity.promotion")
public class PromotionListener {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private void listenPromotion(PromotionMessage msg) {
        log.info("processing promotionCode: {}",
                msg.getPromotionCode());
    }

    private void listenDiscount(DiscountMessage msg) {
        log.info("processing discountCode : {}, and discount  percentage: {}",
                msg.getDiscountCode(), msg.getDiscountPercentage());
    }
}
