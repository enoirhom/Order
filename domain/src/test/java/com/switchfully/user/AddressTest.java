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
        int postCode;

        @BeforeEach
        void setUp() {
            street = "Customer Street";
            house = "8B";
            town = "Customer Town";
            postCode = 6666;
        }

        @Test
        void AddressCreation_givenValidValues_thenAddressIsCreated() {
            Address actual = new Address(street, house, town, postCode);

            Assertions.assertThat(actual.getStreet()).isEqualTo(street);
            Assertions.assertThat(actual.getHouseNumber()).isEqualTo(house);
            Assertions.assertThat(actual.getCity()).isEqualTo(town);
            Assertions.assertThat(actual.getZipCode()).isEqualTo(postCode);
        }
    }
}