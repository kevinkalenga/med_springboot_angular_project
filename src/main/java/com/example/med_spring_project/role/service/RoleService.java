package com.example.med_spring_project.role.service;

import com.example.med_spring_project.res.Response;
import com.example.med_spring_project.role.entity.Role;
import java.util.List;

public interface RoleService {
    Response<Role> createRole(Role roleRequest);

    Response<Role> updateRole(Role roleRequest);

    Response<List<Role>> getAllRoles();;

    Response<?> deleteRole(Long id);
}
