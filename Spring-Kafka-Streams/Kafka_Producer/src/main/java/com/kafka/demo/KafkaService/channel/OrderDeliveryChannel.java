package com.kafka.demo.KafkaService.channel;

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

    @Input(ORDER_GENERATION_REQUESTED)
    SubscribableChannel listen();
}
