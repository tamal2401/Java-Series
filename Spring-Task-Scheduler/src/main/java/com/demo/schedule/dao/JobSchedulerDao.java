package com.demo.schedule.dao;

import com.demo.schedule.job.ScheduleJob;

import java.util.List;
import java.util.Map;

public interface JobSchedulerDao {

    List<ScheduleJob> findAllJobs();

    List<ScheduleJob> findAllActiveJobs();

    ScheduleJob findJobById(String jobId);

    ScheduleJob findJobByName(String name);

    ScheduleJob save(ScheduleJob newJob);

    ScheduleJob updateJob(Map<String, Object> jobConfig, String jobName);

    Long jobCount();

}
