package com.example.demo.services;

import com.example.demo.entities.Staff;

import java.util.List;

public interface StaffService {
    // Add
    Staff saveStaff(Staff staff);

    // Read List
    List<Staff> fetchStaffList();

    // Update
    Staff updateStaff(Staff staff, int staffID);

    // Delete

    void deleteStaffByID(int staffID);

}
