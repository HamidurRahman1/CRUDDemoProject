package com.hamidur.gainam.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer
{
    @Id
    @Column(name = "customer_id", updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    @Column(name = "first_name", updatable = true, unique = false)
    @NotNull(message = "first name cannot be null")
    @NotBlank(message = "first name has too many white spaces")
    @Size(min = 2, max = 30, message = "first name must be in length of 2 to 30 characters")
    @Pattern(regexp = "^(?![\\s.]+$)[a-zA-Z\\s.]*$", message = "first name contains unexpected characters")
    private String firstName;


    @Column(name = "last_name", updatable = true, nullable = true, unique = false)
    @Size(min = 2, max = 30, message = "last name must be in length of 2 to 30 characters")
    @Pattern(regexp = "^(?![\\s.]+$)[a-zA-Z\\s.]*$", message = "last name contains unexpected characters")
    private String lastName;

    @Column(name = "email", updatable = true, nullable = false, unique = true)
    @NotNull(message = "email cannot be null for a customer")
    @Size(min = 3, max = 80, message = "email is too long to store")
    @Pattern(regexp = "^[A-Za-z0-9_.-]+@[A-Za-z0-9_.-]+$",
            message = "email and domain must only contain [letters, digits, dash, underscore, dot] only")
    private String email;

    @Column(name = "country")
    @NotNull(message = "country cannot be null")
    @NotBlank(message = "country has too many white spaces")
    @Size(min = 2, max = 25, message = "Country name must be in 2-25 characters")
    private String country;

    public Customer() {
    }

    public Customer(Integer customerId, String firstName, String lastName, String email, String country) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getCustomerId(), customer.getCustomerId()) &&
                Objects.equals(getFirstName(), customer.getFirstName()) &&
                Objects.equals(getLastName(), customer.getLastName()) &&
                Objects.equals(getEmail(), customer.getEmail()) &&
                Objects.equals(getCountry(), customer.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getFirstName(), getLastName(), getEmail(), getCountry());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
