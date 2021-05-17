package com.kafka.subscriber.order.confirmation.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
public class Commodity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String categoryName;
    private long categoryId;
    private int quantity;
    private String productName;
}
