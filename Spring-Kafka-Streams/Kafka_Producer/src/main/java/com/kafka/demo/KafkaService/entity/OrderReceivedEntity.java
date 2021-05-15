package com.kafka.demo.KafkaService.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Order_Received")
@Data
@NoArgsConstructor
@ToString
public class OrderReceivedEntity {

    public long id;
    private String eventId;
    private String processedDateTime;
    private String paymentMode;
    private String transactionId;
    private String categoryName;
    private long categoryId;
    private int quantity;
    private String productName;
}
