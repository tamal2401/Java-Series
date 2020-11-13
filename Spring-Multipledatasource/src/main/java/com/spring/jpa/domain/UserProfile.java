package com.spring.jpa.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="user_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "up_id", updatable = true, nullable = false)
    private Integer upId;

    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Column(name = "current_job_title", nullable = false)
    private String currentJobTitle;

}
