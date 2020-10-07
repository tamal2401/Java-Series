package com.spring.jpa.custom.domain;

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

    @Column(name = "industry", nullable = false)
    private String industry;

    @Column(name = "job_category_id", nullable = false)
    private Integer jobCategoryId;

    @Column(name = "job_function_id", nullable = false)
    private Integer jobFunctionId;

    @Column(name = "job_class_id", nullable = false)
    private Integer jobClassId;

    @Column(name = "primary_skill_id", nullable = false)
    private Integer primarySkillId;

    @Column(name = "project_opted", nullable = false)
    private String projectOpted;

    @Column(name = "project_role_id", nullable = false)
    private Integer roleId;

}
