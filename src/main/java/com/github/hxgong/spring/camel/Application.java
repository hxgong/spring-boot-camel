package com.github.hxgong.spring.camel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Override
	public void run(String... args) {
		System.out.println("starting boot");
	}

	public static void main(String[] args) throws Exception {
		SpringApplication application = new SpringApplication(
				Application.class);
		application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
		SpringApplication.run(Application.class, args);
	}
}
