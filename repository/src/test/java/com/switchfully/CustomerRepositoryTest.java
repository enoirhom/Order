package com.switchfully;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerRepositoryTest {
    CustomerRepository customerRepository;
    Address randomAddress;

    @BeforeEach
    void setup() {
        customerRepository = new CustomerRepository();
        randomAddress = new Address("Castle Street", "8B", "Winterfell", "6666");
    }

    @Test
    void addCustomer_givenCustomer_thenGetCustomerReturnsCustomer() {
        Customer customer = new Customer("Jon", "Snow", "jon@snow.com", randomAddress,"046532165");

        customerRepository.addCustomer(customer);
        Customer actual = customerRepository.getCustomerById(customer.getId());

        Assertions.assertThat(actual).isEqualTo(customer);
    }

}