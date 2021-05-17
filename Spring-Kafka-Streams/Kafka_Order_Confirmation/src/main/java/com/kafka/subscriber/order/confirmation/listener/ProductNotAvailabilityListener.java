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

import javax.validation.Valid;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
@EnableBinding(OrderConfirmationChannel.class)
public class ProductNotAvailabilityListener {

    private static final Logger log = LoggUtil.getLogger(ProductNotAvailabilityListener.class);

    public static final String CASH = "cash";

    @StreamListener(value = OrderConfirmationChannel.PRODUCT_NOT_AVAILABLE)
    public void listen(Message<OrderModel> message, @Headers Map<String, String> headers) throws Exception {
        log.info("Not available product is: {}", message.getPayload());
        OrderModel model = message.getPayload();

        if (!validate(model)) {
            log.error("received corrupted request order :: {}", model);
            throw new Exception("corrupted order model received");
        }

        if(!CASH.equalsIgnoreCase(model.getPaymentMode())){
            initiateRefund(model.getTransactionId(), model.getUser());
        }
    }

    private void initiateRefund(String transactionId, User user) {
        // Call payment team api with transactionId to initiate refund
    log.info("Refund initiated against transactionId {}, for user {}, mailId : {}", transactionId, user.getUserName(), user.getMail());
    }

    private boolean validate(OrderModel model) {
        return true;
    }
}
