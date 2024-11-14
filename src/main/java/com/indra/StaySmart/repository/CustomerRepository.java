package com.indra.StaySmart.repository;

import com.indra.StaySmart.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, UUID> {


    //     @Query(value = "select c from Customer c where c.email =:email",nativeQuery = false)
    List<Customer> findByEmail(String email);

//    findByAgeGreaterThanAndLessThan(Integer age1 ,Integer age2);

}
