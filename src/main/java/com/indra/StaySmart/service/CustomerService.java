package com.indra.StaySmart.service;

import com.indra.StaySmart.entity.AdharDetails;
import com.indra.StaySmart.entity.Customer;
import com.indra.StaySmart.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Boolean createCustomer(Customer customer) {
        AdharDetails adharDetails=customer.getAdharDetails();
        adharDetails.setCustomer(customer);
        customerRepository.save(customer);
        return  true;

    }

    public Customer getCustomerDetails(UUID customerId) {
        Optional<Customer> customer=customerRepository.findById(customerId);
        return  customer.get();

    }

    public List<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer getCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.orElse(null);
    }

    public Boolean updateCustomer(UUID customerId, Customer updatedCustomer) {
        Optional<Customer> existingCustomer = customerRepository.findById(customerId);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setAdharDetails(updatedCustomer.getAdharDetails());
            customerRepository.save(customer);
            return true;
        }
        return false;
    }

    public Boolean deleteCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return true;
        }
        return false;
    }
}
