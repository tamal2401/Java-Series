package com.demo.schedule.dao;

import com.demo.schedule.job.ScheduleJob;
import com.demo.schedule.repo.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JobSchedulerDaoImpl implements JobSchedulerDao{

    private static final String CLASS_NAME = MethodHandles.lookup().lookupClass().getName();

    private static final Logger log = LoggerFactory.getLogger(CLASS_NAME);

    @Autowired
    JobRepository jobRepository;

    @Override
    public List<ScheduleJob> findAllJobs() {
        List<ScheduleJob> allJobs = jobRepository.findAll();
        return allJobs;
    }

    @Override
    public List<ScheduleJob> findAllActiveJobs() {
        List<ScheduleJob> jobs = findAllJobs();
        List<ScheduleJob> activeJobs = jobs.stream()
                .filter(each -> each.getIsActive().equalsIgnoreCase("y"))
                .collect(Collectors.toList());
        return activeJobs;
    }

    @Override
    public ScheduleJob findJobById(String jobId) {
        Optional<ScheduleJob> job = jobRepository.findById(jobId);
        return job.orElse(null);
    }

    @Override
    public ScheduleJob findJobByName(String name) {
        Collection<ScheduleJob> jobs = jobRepository.findJobByName(name);
        if(jobs.isEmpty()){
            return null;
        }
        return jobs.stream().findFirst().get();
    }

    @Override
    public ScheduleJob save(ScheduleJob newJob) {
        return jobRepository.save(newJob);
    }

    @Override
    public ScheduleJob updateJob(Map<String, Object> jobConfig, String jobName) {
        return null;
    }

    @Override
    public Long jobCount() {
        return null;
    }
}
