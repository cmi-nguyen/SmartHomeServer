package com.example.demo.services;

import com.example.demo.entities.Customer;

import java.util.List;

public interface CustomerService {
    // Save Operation
    Customer saveCustomer(Customer customer);

    // Read Operation
    List<Customer> fetchCustomerList();

    // Update Operation
    Customer updateCustomer(Customer customer,int id_KH);

    // Delete Operation

    void deleteCustomerByID(int id_KH);


}
