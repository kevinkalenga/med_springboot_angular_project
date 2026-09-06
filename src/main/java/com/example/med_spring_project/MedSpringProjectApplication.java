package com.example.med_spring_project;

import com.example.med_spring_project.notification.dto.NotificationDTO;
import com.example.med_spring_project.notification.service.NotificationService;
import com.example.med_spring_project.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@RequiredArgsConstructor
public class MedSpringProjectApplication {

	private final NotificationService notificationService;

	public static void main(String[] args) {
		SpringApplication.run(MedSpringProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(){
		return args -> {
			NotificationDTO notificationDTO = NotificationDTO.builder()
					.recipient("kevinkalenga10@gmail.com")
					.subject("Testing Email")
					.message("Hey, this is a test mail")
					.build();
			notificationService.sendEmail(notificationDTO, new User());
		};
	}

}
