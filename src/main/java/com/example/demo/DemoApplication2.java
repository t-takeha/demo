package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, JndiDataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages= {"com.example.demo"})
public class DemoApplication2 {

	public static void main(String[] args) {
		System.out.println("### START main ###");
		SpringApplication.run(DemoApplication2.class, args);
	}

}
