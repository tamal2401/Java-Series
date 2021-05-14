package com.course.kafka.listener;

import com.course.kafka.broker.message.OrderMessage;
import com.course.kafka.broker.message.OrderReplyMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class OrderReplyListener {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @KafkaListener(topics = "t.comodity.order")
    @SendTo("t.comodity.order-reply")
    public OrderReplyMessage listen(ConsumerRecord<String, OrderMessage> record) {
        String orderNumber = record.key();
        Headers headers = record.headers();
        OrderMessage msg = record.value();

        log.info("processing order: {}, item: {}, credit card number: {}", msg.getOrderNumber(), msg.getItemName(), msg.getCreditCardNumber());

        log.info("Headers are");
        headers.forEach(each -> log.info("header key: {}, value: {}", each.key(), new String(each.value())));

        double bounsPercentage = Double.parseDouble(new String(headers.lastHeader("surprisePercentage").value()));
        int bonusAmount = (int) ((bounsPercentage / 100) * msg.getQuantity() * msg.getPrice());

        log.info("Bonus amount is: {}", bonusAmount);

        return new OrderReplyMessage(String.format("Order: %s, item: %s, is processed", msg.getOrderNumber(), msg.getItemName()));
    }
}
