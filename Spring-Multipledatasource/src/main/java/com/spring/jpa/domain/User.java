package com.spring.jpa.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = true, nullable = false)
    private Integer userId;

    @Column(name = "email_id", nullable = false)
    private String fName;

    @Column(name = "first_name", nullable = false)
    private String lName;

}
