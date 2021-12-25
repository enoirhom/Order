package com.switchfully.customer;

import com.switchfully.user.Customer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile("!test")
public class CustomerRepositoryJpa implements CustomerRepository {
    private final Map<String, Customer> customers;

    public CustomerRepositoryJpa() {
        customers = new ConcurrentHashMap<>();
    }

    @Override
    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    @Override
    public Customer getCustomerById(String id) {
        return customers.get(id);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customers.values().stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers.values().stream().toList();
    }
}
