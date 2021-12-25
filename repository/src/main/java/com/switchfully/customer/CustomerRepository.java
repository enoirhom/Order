package com.switchfully.customer;

import com.switchfully.user.Customer;

import java.util.List;

public interface CustomerRepository {
    void addCustomer(Customer customer);
    Customer getCustomerById(String id);
    Customer getCustomerByEmail(String email);
    List<Customer> getAllCustomers();
}
