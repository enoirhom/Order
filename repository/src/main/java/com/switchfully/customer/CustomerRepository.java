package com.switchfully.customer;

import com.switchfully.user.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    void addCustomer(Customer customer);
    Optional<Customer> findCustomerById(UUID id);
    Optional<Customer> findCustomerByEmail(String email);
    List<Customer> getAllCustomers();
}
