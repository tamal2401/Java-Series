package com.kafka.demo.order.generation.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class User {
    private String userId;
    private String userName;
    private String mail;
}
