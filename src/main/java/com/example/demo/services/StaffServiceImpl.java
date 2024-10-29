package com.example.demo.services;

import com.example.demo.entities.Staff;
import com.example.demo.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StaffServiceImpl implements StaffService{
    @Autowired private StaffRepository staffRepository;
    @Override
    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public List<Staff> fetchStaffList() {
        return staffRepository.findAll();
    }

    @Override
    public Staff updateStaff(Staff staff, int staffID) {
        Staff staffDB = staffRepository.findById(staffID).get();
        if (Objects.nonNull(staff.getStaffID())){
            staffDB.setStaffID(staff.getStaffID());
        }
        if(Objects.nonNull(staff.getEmail())&&!"".equalsIgnoreCase(staff.getEmail())){
            staffDB.setEmail(staff.getEmail());
        }
        if (Objects.nonNull(staff.getFirstName())&&!"".equalsIgnoreCase(staff.getFirstName())){
            staffDB.setFirstName(staff.getFirstName());
        }
        if (Objects.nonNull(staff.getLastName())&&!"".equalsIgnoreCase(staff.getLastName())){
            staffDB.setLastName(staff.getLastName());
        }
        if (Objects.nonNull(staff.getPhone())){
            staffDB.setPhone(staff.getPhone());
        }
        if (Objects.nonNull(staffDB.getStatus())){
            staffDB.setStatus(staff.getStatus());
        }
        return staffRepository.save(staffDB);
    }

    @Override
    public void deleteStaffByID(int staffID) {
        staffRepository.deleteById(staffID);
    }
}
