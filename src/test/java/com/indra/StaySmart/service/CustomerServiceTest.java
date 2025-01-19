//package com.indra.StaySmart.service;
//
//import com.indra.StaySmart.entity.AdharDetails;
//import com.indra.StaySmart.entity.Customer;
//import com.indra.StaySmart.repository.CustomerRepository;
//import com.indra.StaySmart.service.CustomerService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//
//@SpringBootTest
//public class CustomerServiceTest {
//
//    @Autowired
//    private CustomerService customerService;
//
//    @MockBean
//    private CustomerRepository customerRepository;
//
//    @Test
//    public void testCreateCustomer_Success() {
//        Customer customer = new Customer();
//        customer.setName("John Doe");
//        customer.setPhoneNumber("1234567890");
//        customer.setEmail("johndoe@example.com");
//
//        AdharDetails adharDetails = new AdharDetails();
////        adharDetails.setAdharNumber("123456789012");
//        customer.setAdharDetails(adharDetails);
//
//        Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(customer);
//
////        Boolean result = customerService.addUser(customer);
//
////        assertTrue(result);
//    }
//}