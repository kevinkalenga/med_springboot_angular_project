package com.example.med_spring_project.users.repo;

import com.example.med_spring_project.users.entity.PasswordResetCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetRepo extends JpaRepository<PasswordResetCode, Long> {
    Optional<PasswordResetCode> findByCode(String code);
    void deleteByUserId(Long userId);
}
