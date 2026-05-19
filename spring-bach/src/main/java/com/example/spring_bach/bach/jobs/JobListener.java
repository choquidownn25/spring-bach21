package com.example.spring_bach.bach.jobs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class JobListener implements JobExecutionListener {

    private final Logger logger =  LoggerFactory.getLogger(JobListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Job is starting...");
        logger.info("Job Started: " + jobExecution.getJobInstance().getJobName());
        logger.info("Start time: " + jobExecution.getStartTime());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Job has finished.");
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            logger.info("Job Completed");
        }
    }
}
