package com.kafka.demo.KafkaService.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Data
@NoArgsConstructor
@ToString
public class OrderModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private long orderId;
    private String eventId;
    private String eventType;
    private String processedDateTime;
    private String paymentMode;
    private String transactionId;
    @Valid
    private Commodity commodity;
}
