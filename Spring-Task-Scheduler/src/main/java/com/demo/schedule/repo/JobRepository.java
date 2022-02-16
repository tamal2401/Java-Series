package com.demo.schedule.repo;

import com.demo.schedule.job.ScheduleJob;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface JobRepository extends MongoRepository<ScheduleJob, String> {

    @Query("{ 'jobName' : ?0 }")
    Collection<ScheduleJob> findJobByName(String jobName);

}
