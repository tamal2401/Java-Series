package com.kafka.subscriber.order.confirmation.listener;

import com.kafka.subscriber.order.confirmation.channel.OrderConfirmationChannel;
import com.kafka.subscriber.order.confirmation.model.Commodity;
import com.kafka.subscriber.order.confirmation.model.OrderModel;
import com.kafka.subscriber.order.confirmation.model.User;
import com.kafka.subscriber.order.confirmation.util.LoggUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@EnableBinding(OrderConfirmationChannel.class)
public class ProductAvailabilityListener {

    private static final Logger log = LoggUtil.getLogger(ProductAvailabilityListener.class);

    @StreamListener(value = OrderConfirmationChannel.PRODUCT_AVAILABLE)
    public void listen(Message<OrderModel> message, @Headers Map<String, String> headers) throws Exception {
        log.info("Available product is: {}", message.getPayload());
        OrderModel model = message.getPayload();

        if (!validate(model)) {
            log.error("received corrupted request order :: {}", model);
            throw new Exception("corrupted order model received");
        }

        notifyUser(model.getUser());
    }

    private void notifyUser(User user) {
        log.info("mail sent to user {} on mail id => {}", user.getUserName(), user.getMail());
    }

    private boolean validate(OrderModel model) {
        return true;
    }
}
