package com.example.med_spring_project.appointment.dto;


import com.example.med_spring_project.doctor.dto.DoctorDTO;
import com.example.med_spring_project.doctor.entity.Doctor;
import com.example.med_spring_project.enums.AppointmentStatus;
import com.example.med_spring_project.patient.dto.PatientDTO;
import com.example.med_spring_project.patient.entity.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppointmentDTO {


    private Long id;

    @NotNull(message = "Doctor ID is required for booking an appointment.")
    private Long doctorId;

    private String purposeOfConsultation;

    private String initialSymptoms;

    @NotNull(message = "Start time is required for the appointment.")
    @Future(message = "Appointment must be scheduled for a future date and time")
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String meetingLink;


    private AppointmentStatus status;


    private DoctorDTO doctor;


    private PatientDTO patient;


}
