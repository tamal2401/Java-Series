package com.demo.schedule.service;

import com.demo.schedule.exception.CommonException;
import com.demo.schedule.job.ScheduleJob;
import org.quartz.SchedulerException;
import org.springframework.boot.CommandLineRunner;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface SchedulerService extends CommandLineRunner {

    List<ScheduleJob> getJobs();

    String activateJob(String jobName) throws CommonException, SchedulerException, ParseException;

    String deActivate(String jobName) throws SchedulerException, CommonException;

    String deleteJob(String jobName) throws CommonException, SchedulerException;

    void reloadJobs() throws CommonException;

    void startScheduler() throws CommonException;

    void shutdownScheduler() throws CommonException;

    void forceShutdownScheduler() throws CommonException;

    void triggerNow(String jobName) throws SchedulerException;

    ScheduleJob createScheduleJob(ScheduleJob newJob) throws SchedulerException, CommonException, ParseException;

    ScheduleJob updateJob(String jobName, Map<String, Object> jobConfig);

    boolean isServiceDormant();

}
