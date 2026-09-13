package com.example.med_spring_project.role.repo;

import com.example.med_spring_project.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    @Override
    Optional<Role> findByName(String name);
}
