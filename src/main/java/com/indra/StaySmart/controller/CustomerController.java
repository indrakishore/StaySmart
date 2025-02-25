package com.indra.StaySmart.controller;

import com.indra.StaySmart.dto.request.CustomerRequestDto;
import com.indra.StaySmart.entity.Customer;
import com.indra.StaySmart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/add")
    public String createCustomer(@RequestBody CustomerRequestDto customer){
        return customerService.addUser(customer);

    }

    @GetMapping("/")
    public Customer getCustomer(@RequestParam UUID customerId){
        return customerService.getCustomerDetails(customerId);

    }

    @GetMapping("/getByEmail")
    public List<Customer> getCustomerByEmail(@RequestParam("email") String email)
    {

        return customerService.findByEmail(email);



    }

}