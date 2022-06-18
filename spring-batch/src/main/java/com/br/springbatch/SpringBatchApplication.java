package com.br.springbatch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatchApplication implements CommandLineRunner {
	
	@Autowired
    private Job job;

	@Autowired
    private JobLauncher jobLauncher;
    
	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(true) {
			System.out.println("executa");
			jobLauncher.run(job, newExecution());
		} else {
			System.out.println("nao executa");
		}
		
	}
	
	private JobParameters newExecution() {
        Map<String, JobParameter> parameters = new HashMap<>();
        JobParameter parameter = new JobParameter(new Date());
        parameters.put("currentTime", parameter);
        return new JobParameters(parameters);
    }
	
}
