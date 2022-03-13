package com.demo.schedule.service;

import com.demo.schedule.dao.JobSchedulerDao;
import com.demo.schedule.exception.CommonException;
import com.demo.schedule.job.ScheduleJob;
import com.demo.schedule.logger.LoggerEntry;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private static final String CLASS_NAME = MethodHandles.lookup().lookupClass().getName();

    private static final Logger log = LoggerFactory.getLogger(CLASS_NAME);

    private static boolean serviceDormant;

    @Autowired
    TaskService taskService;

    @Autowired
    Scheduler scheduler;

    @Autowired
    JobSchedulerDao jobSchedulerDao;

    /**
     * To trigger a thread to initialize scheduler
     *
     * @param args
     * @throws Exception
     */
    @LoggerEntry
    @Override
    public void run(String... args) throws Exception {
        Thread th = new Thread(() -> {
            try {
                if (!scheduler.isStarted()) {
                    scheduler.start();
                }
                loadJobs();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        th.start();
        serviceDormant = false;
    }

    /**
     * To get all jobs
     *
     * @return List<ScheduleJob>
     */
    @Override
    public List<ScheduleJob> getJobs() {
        return jobSchedulerDao.findAllJobs();
    }

    /**
     * To activate a job
     *
     * @param jobName
     * @return String
     * @throws CommonException
     * @throws SchedulerException
     * @throws ParseException
     */
    @Override
    public String activateJob(String jobName) throws CommonException, SchedulerException, ParseException {
        ScheduleJob job = jobSchedulerDao.findJobByName(jobName);
        if (null == job) {
            throw new CommonException(jobName + "Job doesn't exist");
        }
        job.setIsActive("y");

        JobKey key = new JobKey(jobName);
        if (scheduler.checkExists(key)) {
            scheduleJob(job);
        } else {
            scheduler.resumeJob(key);
        }
        jobSchedulerDao.save(job);
        return jobName + "Job is activated";
    }

    /**
     * To deactivate a job
     *
     * @param jobName
     * @return String
     * @throws CommonException
     * @throws SchedulerException
     * @throws ParseException
     */
    @Override
    public String deActivate(String jobName) throws SchedulerException, CommonException {
        ScheduleJob job = jobSchedulerDao.findJobByName(jobName);
        if (null == job) {
            throw new CommonException(jobName + "Job doesn't exist");
        }
        inActivateJob(job);
        return jobName + " : job is deactivated";
    }

    /**
     * To delete a job
     *
     * @param jobName
     * @return String
     * @throws CommonException
     * @throws SchedulerException
     * @throws ParseException
     */
    @Override
    public String deleteJob(String jobName) throws CommonException, SchedulerException {
        ScheduleJob job = jobSchedulerDao.findJobByName(jobName);
        if (null == job) {
            throw new CommonException(jobName + "Job doesn't exist");
        }
        scheduler.deleteJob(new JobKey(job.getJobName()));
        job.setIsActive("n");
        jobSchedulerDao.save(job);
        return jobName + " : job has been deleted";
    }

    /**
     * To reload all jobs
     *
     * @return String
     * @throws CommonException
     */
    @Override
    public void reloadJobs() throws CommonException {
        loadJobs();
    }

    /**
     * To start the scheduler
     *
     * @throws CommonException
     */
    @Override
    @LoggerEntry
    public void startScheduler() throws CommonException {
        try {
            if (!scheduler.isStarted()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * To shutdown a scheduler
     *
     * @throws CommonException
     */
    @Override
    public void shutdownScheduler() throws CommonException {
        try {
            if (scheduler.isStarted()) {
                scheduler.shutdown(true);
            }
        } catch (SchedulerException e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * To force shutdown a scheduler
     *
     * @throws CommonException
     */
    @Override
    public void forceShutdownScheduler() throws CommonException {
        try {
            if (scheduler.isStarted()) {
                scheduler.shutdown();
            }
        } catch (SchedulerException e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * To trigger a job now
     *
     * @param jobName
     * @throws SchedulerException
     */
    @Override
    public void triggerNow(String jobName) throws SchedulerException {
        log.info("Triggers of job: " + scheduler.getTriggersOfJob(new JobKey(jobName)).size());
        scheduler.triggerJob(new JobKey(jobName));
    }

    /**
     * To create and scheduler a job
     *
     * @param newJob
     * @return
     * @throws SchedulerException
     * @throws CommonException
     * @throws ParseException
     */
    @Override
    public ScheduleJob createScheduleJob(ScheduleJob newJob) throws SchedulerException, CommonException, ParseException {
        if (scheduler.checkExists(new JobKey(newJob.getJobName()))) {
            throw new CommonException("job already exists and running");
        }
        ScheduleJob job = jobSchedulerDao.save(newJob);
        if ("y".equalsIgnoreCase(job.getIsActive())) {
            scheduleJob(job);
        }
        return job;
    }

    /**
     * To update a job
     *
     * @param jobName
     * @param jobConfig
     * @return
     */
    @Override
    public ScheduleJob updateJob(String jobName, Map<String, Object> jobConfig) {
        return null;
    }

    /**
     * To check if the scheduler is dormant
     *
     * @return
     */
    @Override
    public boolean isServiceDormant() {
        return serviceDormant;
    }

    /**
     * To scheduler a job
     *
     * @param job
     * @throws SchedulerException
     * @throws ParseException
     */
    private void scheduleJob(ScheduleJob job) throws SchedulerException, ParseException {
        scheduler.scheduleJob(crateJob(job), createTrigger(job));
    }

    /**
     * To reschedule a job
     *
     * @param job
     * @throws SchedulerException
     * @throws ParseException
     */
    private void reScheduleJob(ScheduleJob job) throws SchedulerException, ParseException {
        scheduler.rescheduleJob(scheduler.getTriggersOfJob(new JobKey(job.getJobName()))
                .stream()
                .findFirst()
                .get()
                .getKey(), createTrigger(job));
    }

    /**
     * To create a specific trigger
     *
     * @param job
     * @return
     * @throws ParseException
     */
    private Trigger createTrigger(ScheduleJob job) throws ParseException {
        switch (job.getJobType()) {
            case "cron":
                return createCronTrigger(job);
            case "recursive":
                return createRecursiveTrigger(job);
            case "manual":
                return createManualTrigger(job);
            default:
                return simpleTrigger(job);
        }
    }

    /**
     * Create simple trigger
     *
     * @param job
     * @return
     * @throws ParseException
     */
    private Trigger simpleTrigger(ScheduleJob job) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(job.getStartTime());
        return TriggerBuilder.newTrigger()
                .withIdentity(job.getJobName())
                .startAt(startDate)
                .build();
    }

    /**
     * Create manual trigger
     *
     * @param job
     * @return
     */
    private Trigger createManualTrigger(ScheduleJob job) {
        return TriggerBuilder.newTrigger()
                .withIdentity(job.getJobName())
                .build();
    }

    /**
     * create recursive trigger
     *
     * @param job
     * @return
     * @throws ParseException
     */
    private Trigger createRecursiveTrigger(ScheduleJob job) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(job.getStartTime());
        String triggerName = job.getJobName();
        return TriggerBuilder.newTrigger()
                .withIdentity(triggerName)
                .startAt(startDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(job.getRepeatInterval()).repeatForever())
                .build();
    }

    /**
     * Create cron trigger
     *
     * @param job
     * @return
     */
    private Trigger createCronTrigger(ScheduleJob job) {
        return TriggerBuilder.newTrigger()
                .withIdentity(job.getJobName())
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                .build();
    }

    /**
     * Create a job
     *
     * @param job
     * @return
     */
    private JobDetail crateJob(ScheduleJob job) {
        if ("manual".equalsIgnoreCase(job.getJobType())) {
            return JobBuilder.newJob(CustomJob.class)
                    .withIdentity(job.getJobName())
                    .usingJobData(getDataMap(job))
                    .storeDurably()
                    .build();
        }
        return JobBuilder.newJob(CustomJob.class)
                .withIdentity(job.getJobName())
                .usingJobData(getDataMap(job))
                .build();
    }

    /**
     * Get job datamap
     *
     * @param job
     * @return
     */
    private JobDataMap getDataMap(ScheduleJob job) {
        JobDataMap map = new JobDataMap();
        map.put("schedulerJob", job);
        map.put("taskExecutor", taskService);
        return map;
    }

    /**
     * To inactivate a job
     *
     * @param job
     * @throws SchedulerException
     */
    private void inActivateJob(ScheduleJob job) throws SchedulerException {
        job.setIsActive("n");
        JobKey key = new JobKey(job.getJobName());
        if (scheduler.checkExists(key)) {
            scheduler.pauseJob(key);
        }
        jobSchedulerDao.save(job);
    }

    /**
     * Load all the jobs in scheduler context
     *
     * @throws CommonException
     */
    public void loadJobs() throws CommonException {
        List<ScheduleJob> jobs = jobSchedulerDao.findAllJobs();
        if (null == jobs || jobs.isEmpty()) {
            throw new CommonException("No Job Exists in DB");
        }

        for (ScheduleJob job : jobs) {
            try {
                if ("n".equals(job.getIsActive()) && scheduler.checkExists(new JobKey(job.getJobName()))) {
                    inActivateJob(job);
                    continue;
                }
                if (scheduler.checkExists(new JobKey(job.getJobName()))) {
                    reScheduleJob(job);
                    continue;
                }
                scheduleJob(job);
                if ("n".equals(job.getIsActive())) {
                    inActivateJob(job);
                }
            } catch (Exception ex) {

            }
        }
    }


}
