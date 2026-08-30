package com.example.med_spring_project.doctor.dto;


import com.example.med_spring_project.enums.Specialization;
import com.example.med_spring_project.users.dto.UserDTO;
import com.example.med_spring_project.users.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDTO {


    private Long id;

    private String firstName;
    private String lastName;


    private Specialization specialization;

    private String licenseNumber;

    private UserDTO user;


}
