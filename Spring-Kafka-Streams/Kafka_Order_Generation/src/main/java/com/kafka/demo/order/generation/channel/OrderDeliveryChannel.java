package com.kafka.demo.order.generation.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface OrderDeliveryChannel {
    String ORDER_GENERATION_REQUESTED = "orderGenerationRequested";

    @Output(ORDER_GENERATION_REQUESTED)
    MessageChannel orderGenerationRequested();
}
