package com.switchfully;


import com.switchfully.user.Address;
import com.switchfully.user.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CustomerRepositoryTest {
    CustomerRepository customerRepository;
    Address randomAddress;

    @BeforeEach
    void setup() {
        customerRepository = new CustomerRepository();
        randomAddress = new Address("Customer Street", "8B", "Customer Town", "6666");
    }

    @Test
    void addCustomer_givenValidCustomer_thenGetCustomerReturnCustomer() {
        Customer customer = new Customer("Customer1", "Customer", "customer1@customer.com", randomAddress,"046532165");

        customerRepository.addCustomer(customer);
        Customer actual = customerRepository.getCustomerById(customer.getId());

        Assertions.assertThat(actual).isEqualTo(customer);
    }

    @Test
    void getCustomerByEmail_givenExistingEmail_thenReturnCustomer() {
        Customer customer = new Customer("Customer1", "Customer", "customer1@customer.com", randomAddress,"046532165");

        customerRepository.addCustomer(customer);
        Customer actual = customerRepository.getCustomerByEmail(customer.getEmail());

        Assertions.assertThat(actual).isEqualTo(customer);
    }

    @Test
    void getCustomerByEmail_givenNonExistingEmail_thenReturnNull() {
        populateCustomerRepository();

        Customer actual = customerRepository.getCustomerByEmail("unknow@email.com");

        Assertions.assertThat(actual).isNull();
    }

    @Test
    void getAllCustomers_givenEmptyRepository_thenReturnEmptyList() {
        List<Customer> customers = customerRepository.getAllCustomers();

        Assertions.assertThat(customers).isEmpty();
    }

    @Test
    void getAllCustomers_givenPopulatedRepository_thenReturnCustomersList() {
        List<Customer> expectedCustomers = populateCustomerRepository();

        List<Customer> actualCustomers = customerRepository.getAllCustomers();

        Assertions.assertThat(actualCustomers).containsAll(expectedCustomers);
        Assertions.assertThat(expectedCustomers).containsAll(actualCustomers);
    }

    private List<Customer> populateCustomerRepository() {
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer("Customer1", "Customer", "customer1@customer.com", randomAddress,"046532165");
        Customer customer2 = new Customer("Customer2", "Customer", "customer2@customer.com", randomAddress,"046532166");
        Customer customer3 = new Customer("Customer3", "Customer", "customer3@customer.com", randomAddress,"046532167");

        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);

        customerRepository.addCustomer(customer1);
        customerRepository.addCustomer(customer2);
        customerRepository.addCustomer(customer3);

        return customers;
    }

}