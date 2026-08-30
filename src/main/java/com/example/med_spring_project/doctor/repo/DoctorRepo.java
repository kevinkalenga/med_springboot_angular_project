package com.example.med_spring_project.doctor.repo;

import com.example.med_spring_project.doctor.entity.Doctor;
import com.example.med_spring_project.enums.Specialization;
import com.example.med_spring_project.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    //find the doctor of the user
    Optional<Doctor> findByUser(User user);

    List<Doctor> findBySpecialization(Specialization specialization);
}
