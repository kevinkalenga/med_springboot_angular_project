package com.example.med_spring_project.users.service;

import com.example.med_spring_project.res.Response;
import com.example.med_spring_project.users.dto.LoginRequest;
import com.example.med_spring_project.users.dto.LoginResponse;
import com.example.med_spring_project.users.dto.RegistrationRequest;
import com.example.med_spring_project.users.dto.ResetPasswordRequest;

public interface AuthService {
    Response<String> register(RegistrationRequest request);
    Response<LoginResponse> login(LoginRequest loginRequest);

    Response<?> forgetPassword(String email);

    Response<?> updatePasswordViaResetCode(ResetPasswordRequest resetPasswordRequest);

}
