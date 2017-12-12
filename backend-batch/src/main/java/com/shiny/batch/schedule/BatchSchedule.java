package com.shiny.batch.schedule;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author shiny
 **/
@EnableScheduling
@Component
public class BatchSchedule {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Scheduled(initialDelay = 3000,fixedRate = 1000)
    public void schedule(){
        try {
            JobParameters jobParameters=new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis()).toJobParameters();
            JobExecution execution=jobLauncher.run(job,jobParameters);
            System.out.println("Execution status:"+execution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
