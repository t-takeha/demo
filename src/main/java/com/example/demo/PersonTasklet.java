package com.example.demo;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

//@Component
public class PersonTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("### PersonTasklet#execute ###");

		Person p = new Person();

		p.setFirstName("first");
		p.setLastName("last");

		System.out.println(p);

		return RepeatStatus.FINISHED;
	}

}
