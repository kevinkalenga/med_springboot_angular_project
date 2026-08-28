package com.example.med_spring_project.users.dto;

import com.example.med_spring_project.enums.Specialization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotBlank(message = "Password is required")
    private String password;


    private Specialization specialization; // if user is a doctor specify his specialization

    private String licenseNumber; //if user is a doctor licence number of the doctor


    private List<String> roles;


}
