package com.crud.tasks_new;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//public class TasksApplication extends SpringBootServletInitializer {
public class TasksNewApplication {
	public static void main(String[] args) {
		SpringApplication.run(TasksNewApplication.class, args);
	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(TasksApplication.class);
//	}
}