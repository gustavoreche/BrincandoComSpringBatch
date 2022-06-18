package com.br.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.br.springbatch.tasklet.ReadFileTasklet;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
 
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    ReadFileTasklet readTasklet;
    
    @Bean
    public Job job() {
        return this.jobBuilderFactory
          .get("taskletFileInDatabase")
          .start(readLines())
          .build();
    }
    
    @Bean
    protected Step readLines() {
        return this.stepBuilderFactory
          .get("readFile")
          .tasklet(this.readTasklet)
          .build();
    }

}
