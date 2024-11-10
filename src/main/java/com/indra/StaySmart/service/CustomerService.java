package com.indra.StaySmart.service;

import com.indra.StaySmart.entity.AdharDetails;
import com.indra.StaySmart.entity.Customer;
import com.indra.StaySmart.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Boolean createCustomer(Customer customer) {
//        AdharDetails adharDetails=customer.getAdharDetails();
//        adharDetails.setCustomer(customer);
        customerRepository.save(customer);
        return  true;

    }

    public Customer getCustomerDetails(Integer customerId) {
        Optional<Customer> customer=customerRepository.findById(customerId);
        return  customer.get();

    }

    public List<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);


    }
}
