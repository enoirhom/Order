package com.switchfully.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class CustomerTest {

    @Nested
    class CustomerCreation {
        @Test
        void customerCreation_givenValidValues_thenCustomerIsCreated() {
            String firstname = "Customer1";
            String lastname = "Customer";
            String email = "customer1@customer.com";
            Address address = new Address("Customer Street", "8B", "Customer Town", "6666");
            String phoneNumber = "0489/123123";

            Customer actual = new Customer(firstname, lastname, email, address, phoneNumber);

            Assertions.assertThat(actual.getFirstname()).isEqualTo(firstname);
            Assertions.assertThat(actual.getLastname()).isEqualTo(lastname);
            Assertions.assertThat(actual.getEmail()).isEqualTo(email);
            Assertions.assertThat(actual.getAddress()).isEqualTo(address);
            Assertions.assertThat(actual.getPhoneNumber()).isEqualTo(phoneNumber);
        }
    }


}