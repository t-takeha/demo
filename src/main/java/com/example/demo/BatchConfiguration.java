package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration extends DefaultBatchConfigurer {
	//

	/*
	 * JOB管理テーブルを使わないようにするには、DataSourceを無効にするか
	 * MapベースのJOBリポジトリを使うようにする（＝JobRepository、JobExplorer、JobLauncherをOverrideする）
	 * https://qiita.com/KevinFQ/items/d24de607795f75866864
	 *
	 * 上記は完全に書き換える方式。
	 * DefaultBatchConfigurerの中でデータソースがないときにMapベースのJobリポジトリを作っているので
	 * これをうまく利用するのも手である。
	 */

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    Tasklet tasklet;

    @Bean
    public PersonTasklet tasklet() {
    	System.out.println("### tasklet ###");
        return new PersonTasklet();
    }

    @Bean
    public Step step1() {
    	System.out.println("### step1 ###");
        return stepBuilderFactory.get("step1")
        	//.tasklet(tasklet())
        		.tasklet(tasklet)
            .build();
    }

    @Bean
    public Job job(Step step1) throws Exception {
      System.out.println("### job ###");
      return jobBuilderFactory.get("job")
          .incrementer(new RunIdIncrementer())
          .listener(listener())
          .start(step1)
          .build();
    }

    @Bean
    public JobExecutionListener listener() {
      System.out.println("### listener ###");
      return new JobListener();
    }

}
