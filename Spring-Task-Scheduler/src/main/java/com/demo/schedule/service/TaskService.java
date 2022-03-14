package com.demo.schedule.service;

import com.demo.schedule.job.ScheduleJob;

import java.util.Arrays;

public interface TaskService {

    void executeTask(ScheduleJob currentJob);


}
