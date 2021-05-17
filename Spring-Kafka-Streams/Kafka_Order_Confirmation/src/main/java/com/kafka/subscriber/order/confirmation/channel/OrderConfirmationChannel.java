package com.kafka.subscriber.order.confirmation.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface OrderConfirmationChannel {
    String PRODUCT_NOT_AVAILABLE = "productNotAvailable";

    String PRODUCT_AVAILABLE = "productAvailable";

    @Input(PRODUCT_AVAILABLE)
    SubscribableChannel available();

    @Input(PRODUCT_NOT_AVAILABLE)
    SubscribableChannel notAvailable();
}
