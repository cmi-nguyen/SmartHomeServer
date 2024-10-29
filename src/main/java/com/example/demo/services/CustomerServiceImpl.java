package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    // Read operation implementation
    @Override
    public List<Customer> fetchCustomerList() {
        return customerRepository.findAll();
    }
    // Update
    @Override
    public Customer updateCustomer(Customer customer, int id_KH) {
        Customer cusDB = customerRepository.findById(id_KH).get();
        if (Objects.nonNull(customer.getCustomer_id())){
            cusDB.setCustomer_id(customer.getCustomer_id());
        }
        if  (Objects.nonNull(customer.getFirst_name()) && !"".equalsIgnoreCase(customer.getFirst_name())){
            cusDB.setFirst_name(customer.getFirst_name());
        }
        if (Objects.nonNull(customer.getLast_name()) && !"".equalsIgnoreCase(customer.getLast_name())){
            cusDB.setLast_name(customer.getLast_name());
        }
        if (Objects.nonNull(customer.getEmail()) && !"".equalsIgnoreCase(customer.getEmail())){
            cusDB.setEmail(customer.getEmail());
        }
        if (Objects.nonNull(customer.getPhone())){
            cusDB.setPhone(customer.getPhone());
        }
        if (Objects.nonNull(customer.getStatus())){
            cusDB.setStatus(customer.getStatus());
        }
        return saveCustomer(cusDB);
    }

    // Save operation
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    // Delete Operation
    @Override
    public void deleteCustomerByID(int id_KH) {
        customerRepository.deleteById(id_KH);
    }

}
