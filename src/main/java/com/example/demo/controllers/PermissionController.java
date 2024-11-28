package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PermissionController {
    @Autowired private PermissionService permissionService;

    // Read op mapping
    @CrossOrigin
    @GetMapping("/permissions")
    public List<Permission> fetchPermissionList(){return permissionService.fetchPermissionList();}
    // Save op
    @CrossOrigin
    @PostMapping("/permissions")
    public Permission savePermission(@RequestBody Permission permission){
        return permissionService.savePermission(permission);
    }
    // Update op
    @CrossOrigin
    @PutMapping("/permissions/{id}")
    public Permission updatePermission(@RequestBody Permission permission, @PathVariable("id") int permissionId){
        return permissionService.UpdatePermission(permission,permissionId);
    }

    // Delete op
    @CrossOrigin
    @DeleteMapping("/permissions/{id}")
    public String deletePermission(@PathVariable("id") int permissionId){
        permissionService.deletePermissionByID(permissionId);
        return "Delete Successfull";
    }
}
