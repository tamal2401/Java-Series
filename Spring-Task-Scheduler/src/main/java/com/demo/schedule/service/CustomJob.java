package com.demo.schedule.service;

import com.demo.schedule.job.ScheduleJob;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

public class CustomJob implements Job {


    /**
     * Execute custom job logic
     * @param jobExecutionContext
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        final JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        ScheduleJob job = (ScheduleJob) dataMap.get("scheduleJob");
        TaskService service = (TaskService) dataMap.get("taskExecutor");
        service.executeTask(job);
    }
}
