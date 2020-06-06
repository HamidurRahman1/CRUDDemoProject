package com.hamidur.gainam.repos;

import com.hamidur.gainam.models.Customer;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Repository
@Validated
public interface CustomerRepository extends CrudRepository<Customer, Integer>
{
    Customer findByCustomerId(Integer customerId);
    Customer findByEmail(@NotNull @NotBlank @Pattern(regexp = "^[A-Za-z0-9_.-]+@[A-Za-z0-9_.-]+$") String email);

    @Query(nativeQuery = true,
        value = "select * from customers where first_name = lower(:fName) and last_name = lower(:lName)")
    Set<Customer> getCustomersByFirstNameAndLastName(@NotNull @NotBlank @Param("fName") String first,
                                                     @NotNull @NotBlank @Param("lName")String last);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from  customers where customer_id = (:custId)")
    void deleteCustomerByQuery(@Param("custId") Integer customerId);
}
