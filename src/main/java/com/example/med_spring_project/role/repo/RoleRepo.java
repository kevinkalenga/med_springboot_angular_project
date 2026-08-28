package com.example.med_spring_project.role.repo;

import com.example.med_spring_project.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
