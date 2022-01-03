package com.switchfully.stub;

import com.switchfully.customer.CustomerRepository;
import com.switchfully.user.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CustomerRepositoryStub implements CustomerRepository {
    private final Map<UUID, Customer> customers;

    public CustomerRepositoryStub() {
        customers = new ConcurrentHashMap<>();
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Customer findCustomerById(UUID id) {
        return customers.get(id);
    }

    public Optional<Customer> findCustomerByEmail(String email) {
        return customers.values().stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findAny();
    }

    public List<Customer> getAllCustomers() {
        return customers.values().stream().toList();
    }
}
