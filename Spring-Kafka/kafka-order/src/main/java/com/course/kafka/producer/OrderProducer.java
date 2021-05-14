package com.course.kafka.producer;

import com.course.kafka.broker.message.OrderMessage;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderProducer {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private KafkaTemplate<String, Object> template;

    public void send(OrderMessage msg){
        this.template.send("t.comodity.order", msg).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.info("Order: {}, item: {}, error: {}, Order published failed", msg.getOrderNumber(), msg.getItemName(), throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                OrderMessage msg = (OrderMessage) result.getProducerRecord().value();
                log.info("Order: {}, item: {}, published successfully", msg.getOrderNumber(), msg.getItemName());
            }
        });

        log.info("Dummy msg to verify business flow, Order: {}, item: {}", msg.getOrderNumber(), msg.getItemName());
    }

    private ProducerRecord<String, OrderMessage> buildProducerRecord(OrderMessage msg){
        int bonus = StringUtils.startsWithIgnoreCase(msg.getOrderLocation(), "A") ? 25 : 15;
        List<Header> listofheader = new ArrayList<>();
        RecordHeader header = new RecordHeader("surpriseBonus", Integer.toString(bonus).getBytes());
        listofheader.add(header);
         return new ProducerRecord<>("t.comodity.order", null, msg.getOrderNumber(), msg, listofheader);
    }
}
