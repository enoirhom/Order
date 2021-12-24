package com.switchfully.user;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AddressTest {

    @Nested
    class AddressCreationTest {
        String street;
        String house;
        String town;
        String postCode;

        @BeforeEach
        void setUp() {
            street = "Customer Street";
            house = "8B";
            town = "Customer Town";
            postCode = "6666";
        }

        @Test
        void AddressCreation_givenValidValues_thenAddressIsCreated() {
            Address actual = new Address(street, house, town, postCode);

            Assertions.assertThat(actual.getStreet()).isEqualTo(street);
            Assertions.assertThat(actual.getHouse()).isEqualTo(house);
            Assertions.assertThat(actual.getTown()).isEqualTo(town);
            Assertions.assertThat(actual.getPostCode()).isEqualTo(postCode);
        }
    }
}