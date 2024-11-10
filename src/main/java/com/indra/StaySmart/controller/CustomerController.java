package com.indra.StaySmart.controller;

import com.indra.StaySmart.entity.Customer;
import com.indra.StaySmart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/create")
    public Boolean createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);

    }

    @GetMapping("/")
    public Customer getCustomer(@RequestParam Integer customerId){
        return customerService.getCustomerDetails(customerId);

    }

    @GetMapping("/getByEmail")
    public List<Customer> getCustomerByEmail(@RequestParam("email") String email)
    {

        return customerService.findByEmail(email);



    }

}