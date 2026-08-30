package com.example.med_spring_project.notification.repo;

import com.example.med_spring_project.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification, Long> {
}
