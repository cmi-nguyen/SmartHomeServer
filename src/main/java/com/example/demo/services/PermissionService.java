package com.example.demo.services;



import com.example.demo.entities.Permission;

import java.util.List;

public interface PermissionService {
    // Save operation
    Permission savePermission(Permission permission);

    // Read operation
    List<Permission> fetchPermissionList();

    // Update operation
    Permission UpdatePermission(Permission permission, int permissionId);

    // Delete operation

    void deletePermissionByID(int permissionId);
}
