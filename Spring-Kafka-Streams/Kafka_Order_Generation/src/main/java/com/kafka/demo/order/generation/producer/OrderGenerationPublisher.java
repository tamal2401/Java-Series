package com.kafka.demo.order.generation.producer;

import com.kafka.demo.order.generation.model.OrderModel;
import com.kafka.demo.order.generation.util.LoggUtil;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderGenerationPublisher {

    private static final Logger log = LoggUtil.getLogger(OrderGenerationPublisher.class);

    public void send(OrderModel model, MessageChannel channel) {
        Map<String, String> headers = getHeaders(model);

        Message msg = getMessage(headers, model);

        try {
            channel.send(msg);
            log.info("Message published in topic {} :: {}", model.getEventType(), msg);
        } catch (Exception e) {
            log.error("Exception occOrderGenerationRequestListenerured while publishing msg in topic {}, Message => {}", model.getEventType(), msg);
            log.error(ExceptionUtil.getStackTrace(e));
        }
    }

    public Map<String, String> getHeaders(OrderModel model) {
        Map<String, String> headers = new HashMap<>();
        headers.put("spring.cloud.stream.sendTo.destination", model.getEventType());
        headers.put("order.event.id", model.getEventId());
        return headers;
    }

    public <T> Message<T> getMessage(Map<String, String> headers, T model) {

        MimeType mimeType = MimeType.valueOf(MediaType.APPLICATION_JSON_VALUE);
        MessageHeaderAccessor accessor = new MessageHeaderAccessor();
        accessor.setContentType(mimeType);
        if (headers != null) {
            for (Map.Entry each : headers.entrySet()) {
                accessor.setHeader(each.getKey().toString(), each.getValue());
            }
        }
        return MessageBuilder.withPayload(model).setHeaders(accessor).build();
    }

}
