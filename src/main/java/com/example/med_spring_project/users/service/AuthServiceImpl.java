package com.example.med_spring_project.users.service;

import com.example.med_spring_project.doctor.entity.Doctor;
import com.example.med_spring_project.doctor.repo.DoctorRepo;
import com.example.med_spring_project.exception.BadRequestException;
import com.example.med_spring_project.exception.NotFoundException;
import com.example.med_spring_project.notification.dto.NotificationDTO;
import com.example.med_spring_project.notification.service.NotificationService;
import com.example.med_spring_project.patient.entity.Patient;
import com.example.med_spring_project.patient.repo.PatientRepo;
import com.example.med_spring_project.res.Response;
import com.example.med_spring_project.role.entity.Role;
import com.example.med_spring_project.role.repo.RoleRepo;
import com.example.med_spring_project.security.JwtService;
import com.example.med_spring_project.users.dto.LoginRequest;
import com.example.med_spring_project.users.dto.LoginResponse;
import com.example.med_spring_project.users.dto.RegistrationRequest;
import com.example.med_spring_project.users.dto.ResetPasswordRequest;
import com.example.med_spring_project.users.entity.User;
import com.example.med_spring_project.users.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final NotificationService notificationService;

    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;

    @Value("${password.reset.link}")
    private String resetLink;

    @Value("${login.link}")
    private String loginLink;



    @Override
    public Response<String> register(RegistrationRequest request) {
        //1. Check if user already exists
        if(userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("User with email already exists");
        }

        // Detemine the roles to assign. Default to Patient if none are provided
        List<String> requestedRoleNames = (request.getRoles() != null && !request.getRoles().isEmpty())
                ? request.getRoles().stream().map(String::toUpperCase).toList()
                : List.of("PATIENT");

        boolean isDoctor = requestedRoleNames.contains("DOCTOR");

        if(isDoctor && (request.getLicenseNumber() == null || request.getLicenseNumber().isBlank())) {
            throw new BadRequestException("License number required to register a doctor");
        }

        //2. Load and validate roles from the database
        List<Role> roles = requestedRoleNames.stream()
                .map(roleRepo::findByName)
                .flatMap(Optional::stream)
                .toList();

        if(roles.isEmpty()) {
            throw new NotFoundException("Registration failed: Requested roles were not found in the database.");
        }

        //3. Create and save new user entity
        User newUser = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .roles(roles)
                .build();

        User savedUser = userRepo.save(newUser);

        log.info("New user registered:{} with {} roles.", savedUser.getEmail(), roles.size());

        // 4. Process Profile Creation
        for(Role role : roles) {
            String roleName = role.getName();

            switch (roleName) {
                case "PATIENT":
                    creationPatientProfile(savedUser);
                    log.info("Patient profile created: {}", savedUser.getEmail());
                    break;
                case "DOCTOR":
                    creationDoctorProfile(request, savedUser);
                    log.info("Doctor profile created: {}", savedUser.getEmail());
                    break;
                case "ADMIN":

                    log.info("Admin role assigned to user: {}", savedUser.getEmail());
                    break;
                default:

                    log.warn("Assigned role '{}' has no corresponding profile creation logic.", roleName);
                    break;
            }
        }

        //5. Send welcome email out
        sendRegistrationEmail(request, savedUser);

        //6. Return success response
        return Response.<String>builder()
                .statusCode(200)
                .message("Registration successful. A welcome email has been sent to you.")
                .data(savedUser.getEmail())
                .build();
    }

    @Override
    public Response<LoginResponse> login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public Response<?> forgetPassword(String email) {
        return null;
    }

    @Override
    public Response<?> updatePasswordViaResetCode(ResetPasswordRequest resetPasswordRequest) {
        return null;
    }

    private void creationPatientProfile(User user){
        Patient patient = Patient.builder()
                .user(user)
                .build();
        patientRepo.save(patient);
        log.info("Patient profile created");
    }

    private void creationDoctorProfile(RegistrationRequest request, User user){
        Doctor doctor = Doctor.builder()
                .specialization(request.getSpecialization())
                .licenseNumber(request.getLicenseNumber())
                .user(user)
                .build();
        doctorRepo.save(doctor);
        log.info("Doctor profile created");
    }

    private void sendRegistrationEmail(RegistrationRequest request, User user){
        NotificationDTO welcomeEmail = NotificationDTO.builder()
                .recipient(user.getEmail())
                .subject("Welcome to DAT Health!")
                .templateName("welcome")
                .message("Thank you for registering your account is ready.")
                .templateVariables(Map.of(
                       "name", request.getName(),
                        "loginLink", loginLink
                ))
                .build();
        notificationService.sendEmail(welcomeEmail, user);
    }


}
