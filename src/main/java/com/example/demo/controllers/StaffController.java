package com.example.demo.controllers;

import com.example.demo.entities.Staff;
import com.example.demo.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StaffController {
    @Autowired private StaffService staffService;

    @GetMapping("/staff")
    List<Staff> fetchStaffList(){return staffService.fetchStaffList();}

    @PostMapping("/staff")
    public Staff saveStaff(@RequestBody Staff staff){
        return staffService.saveStaff(staff);
    }

    @PutMapping("/staff/{id}")
    public Staff updateStaff(@RequestBody Staff staff, @PathVariable("id") int staffID){
        return staffService.updateStaff(staff,staffID);
    }
    @DeleteMapping("/staff/{id}")
    public String deleteStaff(@PathVariable("id") int staffID){
        staffService.deleteStaffByID(staffID);
        return "Deleted";
    }
}
