package com.hamidur.gainam.web.rest;

import com.hamidur.gainam.exceptions.custom.CustomerNotFound;
import com.hamidur.gainam.models.Customer;
import com.hamidur.gainam.repos.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api")
@Validated
public class RESTController
{
    private final CustomerRepository customerRepository;

    @Autowired
    public RESTController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @DeleteMapping(value = "/customer/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PositiveOrZero @PathVariable Integer customerId)
    {
        customerRepository.deleteCustomerByQuery(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer)
    {
        return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.OK);
    }

    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> insertCustomer(@Valid @RequestBody Customer customer)
    {
        return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.CREATED);
    }

    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Customer>> getCustomers()
    {
        Set<Customer> customers = new HashSet<>();
        customerRepository.findAll().forEach(customer -> customers.add(customer));

        if(customers.isEmpty())
            throw new CustomerNotFound("No customers to return");
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(value = "/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerById(@PositiveOrZero @PathVariable("customerId") Integer customerId)
    {
        Customer customer = customerRepository.findByCustomerId(customerId);
        if(customer == null) throw new CustomerNotFound("No customer found with id="+customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
