package com.switchfully;


import com.switchfully.customer.CustomerRepository;
import com.switchfully.user.Address;
import com.switchfully.user.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;
    Address randomAddress;

    @BeforeEach
    void setup() {
        randomAddress = new Address("Customer Street", "8B", "Customer Town", 6666);
    }

    @Test
    void addCustomer_givenValidCustomer_thenGetCustomerReturnCustomer() {
        Customer customer = new Customer("Customer1", "Customer", "customer1@customer.com", randomAddress,"046532165");

        customerRepository.addCustomer(customer);
        Optional<Customer> actual = customerRepository.findCustomerById(customer.getId());

        Assertions.assertThat(actual).isPresent().get().isEqualTo(customer);
    }

    @Test
    void getCustomerByEmail_givenExistingEmail_thenReturnCustomer() {
        Customer customer = new Customer("Customer1", "Customer", "customer1@customer.com", randomAddress,"046532165");

        customerRepository.addCustomer(customer);
        Optional<Customer> actual = customerRepository.findCustomerByEmail(customer.getEmail());

        Assertions.assertThat(actual).isPresent().get().isEqualTo(customer);
    }

    @Test
    void getCustomerByEmail_givenNonExistingEmail_thenReturnEmptyOptional() {
        populateCustomerRepository();

        Optional<Customer> actual = customerRepository.findCustomerByEmail("unknow@email.com");

        Assertions.assertThat(actual).isEmpty();
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