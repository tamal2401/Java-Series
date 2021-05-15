package com.kafka.demo.KafkaService.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
public class RequestModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String categoryName;
    private long categoryId;
    private int quantity;
    private String productName;
    private String processedDateTime;
    private String paymentMode;
    private String transactionId;
}
