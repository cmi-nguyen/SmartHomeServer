package com.example.demo.services;

import com.example.demo.entities.Permission;
import com.example.demo.entities.Permission;
import com.example.demo.repositories.PermissionRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class PermissionImpl implements PermissionService{
    @Autowired
    private PermissionRepository permissionRepository;
    
    @Override
    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public List<Permission> fetchPermissionList() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission UpdatePermission(Permission permission, int permissionId) {
        Permission permissionDB = permissionRepository.findById(permissionId).get();
        if (Objects.nonNull(permissionDB.getPermissionID())){
            permissionDB.setPermissionID(permission.getPermissionID());
        }
        if (Objects.nonNull(permission.getPermissionName())&&!"".equalsIgnoreCase(permission.getPermissionName())){
            permissionDB.setPermissionName(permission.getPermissionName());
        }


        return permissionRepository.save(permissionDB);
    }

    @Override
    public void deletePermissionByID(int permissionId) {
         permissionRepository.deleteById(permissionId);
    }
}
