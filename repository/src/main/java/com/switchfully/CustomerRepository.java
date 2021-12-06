package com.switchfully;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CustomerRepository {
    private final Map<String, Customer> customers;

    public CustomerRepository() {
        customers = new ConcurrentHashMap<>();
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Customer getCustomerWith(String id) {
        return customers.get(id);
    }
}
