package com.demo.schedule.controller;

import com.demo.schedule.exception.CommonException;
import com.demo.schedule.job.ScheduleJob;
import com.demo.schedule.mdoel.TaskResponse;
import com.demo.schedule.service.SchedulerService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class SchedulerController {

    private static final String CLASS_NAME = MethodHandles.lookup().lookupClass().getName();

    private static final Logger log = LoggerFactory.getLogger(CLASS_NAME);

    @Autowired
    SchedulerService schedulerService;

    /**
     * Get all job details from DB
     *
     * @return TaskResponse
     */
    @GetMapping("/schedule/jobs")
    public TaskResponse getJobs() {
        List<ScheduleJob> jobs = schedulerService.getJobs();
        return new TaskResponse(jobs);
    }

    /**
     * Activate job based on the passed job name
     *
     * @param jobName
     * @return TaskResponse
     */
    @GetMapping("/schedule/activate/{jobName}")
    public TaskResponse activateJob(@PathVariable("jobName") String jobName) throws CommonException, SchedulerException, ParseException {
        String msg = schedulerService.activateJob(jobName);
        return new TaskResponse(msg);
    }

    /**
     * To delete a job by jobName
     * @param jobName
     * @return TaskResponse
     * @throws CommonException
     * @throws SchedulerException
     * @throws ParseException
     */
    @GetMapping("/schedule/delete/{jobName}")
    public TaskResponse deleteJob(@PathVariable("jobName") String jobName) throws CommonException, SchedulerException, ParseException {
        String msg = schedulerService.deleteJob(jobName);
        return new TaskResponse(msg);
    }

    /**
     * Deactivate job based on the passed job name
     *
     * @param jobName
     * @return TaskResponse
     */
    @GetMapping("/schedule/inactivate/{jobName}")
    public TaskResponse inActivateJob(@PathVariable("jobName") String jobName) throws SchedulerException, CommonException {
        String msg = schedulerService.deActivate(jobName);
        return new TaskResponse(msg);
    }

    /**
     * Reload jobs in context of the scheduler
     *
     * @return TaskResponse
     */
    @GetMapping("/schedule/reload")
    public TaskResponse reloadJobs() throws CommonException {
        schedulerService.reloadJobs();
        return new TaskResponse();
    }

    /**
     * Start scheduler
     *
     * @return TaskResponse
     */
    @GetMapping("/schedule/start")
    public TaskResponse startScheduler() throws CommonException {
        schedulerService.startScheduler();
        return new TaskResponse();
    }

    /**
     * shutdown job
     *
     * @return TaskResponse
     */
    @GetMapping("/schedule/shutdown")
    public TaskResponse shutDownScheduler() throws CommonException {
        schedulerService.shutdownScheduler();
        return new TaskResponse();
    }

    /**
     * Force shutdown job
     *
     * @return TaskResponse
     */
    @GetMapping("/schedule/force/shutdown")
    public TaskResponse forceShutdownScheduler() throws CommonException {
        schedulerService.forceShutdownScheduler();
        return new TaskResponse();
    }

    /**
     * Trigger job based on the job name
     *
     * @param jobName
     * @return
     */
    @GetMapping("/schedule/trigger/{jobName}")
    public TaskResponse scheduleJobNow(@PathVariable("jobName") String jobName) throws SchedulerException {
        schedulerService.triggerNow(jobName);
        return new TaskResponse();
    }

    /**
     * Create new job
     *
     * @param newJob
     * @return TaskResponse
     */
    @GetMapping("/schedule/job/create")
    public TaskResponse createJob(@RequestBody ScheduleJob newJob) throws ParseException, SchedulerException, CommonException {
        ScheduleJob job = schedulerService.createScheduleJob(newJob);
        return new TaskResponse(job.getId() + " created successfully");
    }

    /**
     * Update job based on the configuration
     *
     * @param jobConfig
     * @param jobName
     * @return TaskResponse
     */
    @GetMapping("/schedule/update/{jobName}")
    public TaskResponse updateJob(@RequestBody Map<String, Object> jobConfig, @PathVariable("jobName") String jobName) {
        ScheduleJob job = schedulerService.updateJob(jobName, jobConfig);
        return new TaskResponse(job.getId() + " updated successfully");
    }

    /**
     * Check dormant sattus of job
     *
     * @return TaskResponse
     */
    @GetMapping("/schedule/dormant/status")
    public TaskResponse getDormantStatus() {
        boolean isDormant = schedulerService.isServiceDormant();
        return new TaskResponse("job dormant status : " + isDormant);
    }
}
