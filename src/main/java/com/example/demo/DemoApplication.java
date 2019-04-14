package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

// https://terasoluna-batch.github.io/guideline/5.0.0.RELEASE/ja/Ch02_SpringBatchArchitecture.html

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, JndiDataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages= {"com.example.demo"})
public class DemoApplication {

//	@Autowired
//	JobLauncher jobLauncher1;
//
//	@Autowired
//	Job job;

	public static void main(String[] args) {
		System.out.println("### START main ###");
		ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

		JobParameters jobParameters = new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters();

		DemoApplication demo = new DemoApplication();
		try {
			demo.execute(ctx, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void execute(ConfigurableApplicationContext ctx, JobParameters jobParameters) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
		System.out.println(jobLauncher);

		Job job = ctx.getBean(Job.class);
		System.out.println(job);

		jobLauncher.run(job, jobParameters);
	}

}
