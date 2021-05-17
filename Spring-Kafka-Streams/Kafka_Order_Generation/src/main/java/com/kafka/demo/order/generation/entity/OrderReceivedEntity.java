package com.kafka.demo.order.generation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "order_received")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderReceivedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public long id;

    @Column
    private String eventId;

    @Column
    private String processedDateTime;

    @Column
    private String paymentMode;

    @Column
    private String transactionId;

    @Column
    private String categoryName;

    @Column
    private long categoryId;

    @Column
    private int quantity;

    @Column
    private String productName;
}
