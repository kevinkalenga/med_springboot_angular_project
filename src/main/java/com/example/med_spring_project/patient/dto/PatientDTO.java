package com.example.med_spring_project.patient.dto;


import com.example.med_spring_project.enums.BloodGroup;
import com.example.med_spring_project.enums.Genotype;
import com.example.med_spring_project.users.dto.UserDTO;
import com.example.med_spring_project.users.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDTO {

   private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;


    private String knownAllergies;


    private BloodGroup bloodGroup;

    private Genotype genotype;

    private UserDTO user;



}
