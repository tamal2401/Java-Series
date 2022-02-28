package com.demo.schedule.job;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@Document("Schedule_Job")
public class ScheduleJob implements Serializable {

    private static final long serializeVersionId = 1L;

    @Id
    private String id;
    private String jobName;
    private String jobDescription;
    private String startTime;
    private long repeatInterval;
    private String cronExpression;
    private String isActive;
    private String jobType;
    private String createdAt;
    private String updatedAt;
    private String nextStartTime;

}
