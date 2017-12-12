package com.shiny.batch.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * @author shiny
 **/
@Component
public class JobCompletionListener extends JobExecutionListenerSupport{

    private static final Logger logger= LogManager.getLogger(JobCompletionListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus()== BatchStatus.COMPLETED){
            logger.info("Job finished!verify the result");
            logger.info("对账、统计等!");
        }
    }
}
