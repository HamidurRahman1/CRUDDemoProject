package com.hamidur.gainam.repos;

import com.hamidur.gainam.models.Customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>
{
    Customer findByCustomerId(Integer customerId);
    Customer findByEmail(String email);

    @Query(nativeQuery = true,
        value = "select * from customers where first_name = lower(:fName) and last_name = lower(:lName)")
    Set<Customer> getCustomersByFirstNameAndLastName(@Param("fName") String first, @Param("lName")String last);
}
