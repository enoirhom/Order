package com.switchfully;


import com.switchfully.user.Address;
import com.switchfully.user.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerRepositoryTest {
    CustomerRepository customerRepository;
    Address randomAddress;

    @BeforeEach
    void setup() {
        customerRepository = new CustomerRepository();
        randomAddress = new Address("Customer Street", "8B", "Customer Town", "6666");
    }

    @Test
    void addCustomer_givenCustomer_thenGetCustomerReturnsCustomer() {
        Customer customer = new Customer("Customer1", "Customer", "customer1@customer.com", randomAddress,"046532165");

        customerRepository.addCustomer(customer);
        Customer actual = customerRepository.getCustomerById(customer.getId());

        Assertions.assertThat(actual).isEqualTo(customer);
    }

}