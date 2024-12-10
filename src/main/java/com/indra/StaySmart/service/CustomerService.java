package com.indra.StaySmart.service;

import com.indra.StaySmart.dto.request.CustomerRequestDto;
import com.indra.StaySmart.entity.AdharDetails;
import com.indra.StaySmart.entity.Customer;
import com.indra.StaySmart.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String gmail;


//    public Boolean createCustomer(Customer customer) {
//        AdharDetails adharDetails=customer.getAdharDetails();
//        adharDetails.setCustomer(customer);
//        customerRepository.save(customer);
//        return  true;
//
//    }
    public String addUser(CustomerRequestDto customerRequestDto) {
    // Create and build the Customer entity
    Customer customer = Customer.builder()
            .name(customerRequestDto.getName())
            .email(customerRequestDto.getEmail())
            .phoneNumber(customerRequestDto.getPhone())
            .adharDetails(customerRequestDto.getAdharDetails())
            .build();

    // Save the customer to the database
    customerRepository.save(customer);

    // Prepare and send the welcome email
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(customerRequestDto.getEmail());
    mailMessage.setFrom(gmail); // Use the injected 'gmail' property
    mailMessage.setSubject("Welcome to Hotel Management Application");
    String body = "Hi " + customerRequestDto.getName() + "!\n" +
            "Welcome to Hotel Management Application! Enjoy. Use code WELCOME500 to get ₹500 OFF on bookings.";
    mailMessage.setText(body);

    javaMailSender.send(mailMessage);

    // Return a confirmation message
    return "Customer has been saved to the DB with customerId " + customer.getCustomerId();
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
