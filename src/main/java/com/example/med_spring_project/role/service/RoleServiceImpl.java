package com.example.med_spring_project.role.service;


import com.example.med_spring_project.exception.NotFoundException;
import com.example.med_spring_project.res.Response;
import com.example.med_spring_project.role.entity.Role;
import com.example.med_spring_project.role.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    @Override
    public Response<Role> createRole(Role roleRequest) {
        Role savedRole = roleRepo.save(roleRequest);

        return Response.<Role>builder()
                .statusCode(200)
                .message("Role Saved Successfully")
                .data(savedRole)
                .build();
    }

    @Override
    public Response<Role> updateRole(Role roleRequest) {
        Role role = roleRepo.findById(roleRequest.getId())
                .orElseThrow(()-> new NotFoundException("Role not found"));

        role.setName(roleRequest.getName());

        Role updatedRole = roleRepo.save(role);

        return Response.<Role>builder()
                .statusCode(200)
                .message("Role Updated Successfully")
                .data(updatedRole)
                .build();
    }

    @Override
    public Response<List<Role>> getAllRoles() {
        List<Role> roles = roleRepo.findAll();

        return Response.<List<Role>>builder()
                .statusCode(200)
                .message("Roles retreived successfully")
                .data(roles)
                .build();
    }

    @Override
    public Response<?> deleteRole(Long id) {
        if(!roleRepo.existsById(id)) {
            throw new NotFoundException("Role Not Found");
        }
        roleRepo.deleteById(id);

        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Role deleted successfully")
                .build();
    }
}
