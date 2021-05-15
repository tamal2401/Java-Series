package com.kafka.demo.KafkaService.listener;

import com.kafka.demo.KafkaService.channel.OrderDeliveryChannel;
import com.kafka.demo.KafkaService.model.OrderModel;
import com.kafka.demo.KafkaService.service.OrderGenerationServiceImpl;
import com.kafka.demo.KafkaService.util.LoggUtil;
import org.slf4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@EnableBinding(OrderDeliveryChannel.class)
public class OrderGenerationRequestListener {

    private static final Logger log = LoggUtil.getLogger(OrderGenerationRequestListener.class);

    @StreamListener(value = OrderDeliveryChannel.ORDER_GENERATION_REQUESTED)
    public void listen(Message<OrderModel> message, @Headers Map<String, String> headers) {
        headers.forEach((key, value) -> log.info("key: {}, value: {}", key, value));
        log.info("Message: {}", message.getPayload());
    }
}
