package com.example.med_spring_project.consultation.repo;

import com.example.med_spring_project.consultation.entity.Consultation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsultationRepo extends JpaRepository<Consultation, Long> {

    Optional<Consultation> findByAppointment(Long appointmentId);

    List<Consultation> findByAppointmentPatientIdOrderByConsultationDateDesc(Long patientId);
}
