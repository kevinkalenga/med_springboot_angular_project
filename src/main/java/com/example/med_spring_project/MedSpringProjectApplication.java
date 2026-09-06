package com.example.med_spring_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MedSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedSpringProjectApplication.class, args);
	}

}
