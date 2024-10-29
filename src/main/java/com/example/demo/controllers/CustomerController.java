package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired private CustomerService customerService;

    // Read operation mapping
    @GetMapping("/customers")
    public List<Customer> fetchCustomerList(){
        return customerService.fetchCustomerList();
    }

    // Save operation mapping
    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    // Update operation
    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable("id") int customerId){
        return customerService.updateCustomer(customer,customerId);
    }

    // Delete operation
    @DeleteMapping("/customers/{id}")
    public String deleteCustomerByID(@PathVariable("id") int customerId){
        customerService.deleteCustomerByID(customerId);
        return "Delete successful";
    }
}
