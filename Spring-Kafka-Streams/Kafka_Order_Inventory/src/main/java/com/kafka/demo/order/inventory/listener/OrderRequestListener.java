package com.kafka.demo.order.inventory.listener;

import com.kafka.demo.order.inventory.channel.OrderDeliveryChannel;
import com.kafka.demo.order.inventory.model.Commodity;
import com.kafka.demo.order.inventory.model.OrderModel;
import com.kafka.demo.order.inventory.util.LoggUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
@EnableBinding(OrderDeliveryChannel.class)
public class OrderRequestListener {

    private static final Logger log = LoggUtil.getLogger(OrderRequestListener.class);

    @Autowired
    OrderDeliveryChannel orderDeliveryChannel;

    @StreamListener(value = OrderDeliveryChannel.ORDER_GENERATION_REQUESTED)
    public void listen(Message<OrderModel> message, @Headers Map<String, String> headers) throws Exception {
        log.info("Message: {}", message.getPayload());
        OrderModel model = message.getPayload();

        if (!validate(model)) {
            log.error("received corrupted request order :: {}", model);
            throw new Exception("corrupted order model received");
        }

        boolean isAvailable = checkAvailability(model.getCommodity());

        if(isAvailable){
            orderDeliveryChannel.available().send(message);
        }else{
            orderDeliveryChannel.notAvailable().send(message);
        }
    }

    private boolean checkAvailability(Commodity commodity) {
        // logic to check inventory and return
        int val = ThreadLocalRandom.current().nextInt();
        if (0 != Math.abs(val % 2)) {
            return false;
        }
        return true;
    }

    private boolean validate(OrderModel model) {
        return true;
    }
}
