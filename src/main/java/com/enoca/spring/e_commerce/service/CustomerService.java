package com.enoca.spring.e_commerce.service;

import com.enoca.spring.e_commerce.entity.Customer;
import com.enoca.spring.e_commerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;


    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    public Optional<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        if (customerRepository.existsById(id)) {
            updatedCustomer.setId(id);
            return customerRepository.save(updatedCustomer);
        }
        return null;
    }


    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer addCustomer(Customer customer) {
        return customer;
    }
}