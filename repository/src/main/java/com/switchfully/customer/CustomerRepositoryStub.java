package com.switchfully.customer;

import com.switchfully.customer.CustomerRepository;
import com.switchfully.user.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CustomerRepositoryStub implements CustomerRepository {
    private final Map<String, Customer> customers;

    public CustomerRepositoryStub() {
        customers = new ConcurrentHashMap<>();
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Customer getCustomerById(String id) {
        return customers.get(id);
    }

    public Customer getCustomerByEmail(String email) {
        return customers.values().stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return customers.values().stream().toList();
    }
}
