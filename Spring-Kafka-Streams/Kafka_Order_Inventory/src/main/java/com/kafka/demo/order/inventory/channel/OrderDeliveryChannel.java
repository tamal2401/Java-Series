package com.kafka.demo.order.inventory.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface OrderDeliveryChannel {
    String ORDER_GENERATION_REQUESTED = "orderGenerationRequested";

    String PRODUCT_NOT_AVAILABLE = "productNotAvailable";

    String PRODUCT_AVAILABLE = "productAvailable";

    @Input(ORDER_GENERATION_REQUESTED)
    SubscribableChannel listen();

    @Output(PRODUCT_AVAILABLE)
    MessageChannel available();

    @Output(PRODUCT_NOT_AVAILABLE)
    MessageChannel notAvailable();
}
