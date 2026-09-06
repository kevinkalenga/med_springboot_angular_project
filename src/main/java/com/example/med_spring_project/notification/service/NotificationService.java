package com.example.med_spring_project.notification.service;

import com.example.med_spring_project.notification.dto.NotificationDTO;
import com.example.med_spring_project.users.entity.User;

public interface NotificationService {
    void sendEmail(NotificationDTO notificationDTO, User user);
}
