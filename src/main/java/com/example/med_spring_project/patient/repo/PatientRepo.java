package com.example.med_spring_project.patient.repo;

import com.example.med_spring_project.patient.entity.Patient;
import com.example.med_spring_project.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepo extends JpaRepository<Patient, Long> {
    Optional<Patient> findByUser(User user);
}
