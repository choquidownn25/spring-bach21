package com.example.spring_bach.bach.config;

import com.example.spring_bach.bach.jobs.JobListener;
import com.example.spring_bach.bach.process.ProductoProccessorBach;
import com.example.spring_bach.dto.ProductoDto;
import com.example.spring_bach.entity.Producto;
import lombok.RequiredArgsConstructor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;


import org.springframework.batch.core.step.builder.StepBuilder;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class BatchConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final FlatFileItemReader<ProductoDto> reader;
    private final ProductoProccessorBach processor;
    private final ItemWriter<Producto> writer;

    @Bean
    public Step step1() {

        return new StepBuilder("step-import-productos",
                jobRepository)
                .<ProductoDto, Producto>chunk(100,
                        transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importProductJob(JobListener listener,
                                Step step1) {

        return new JobBuilder("import-product-job",
                jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }
}
