package com.switchfully.user;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class AdminTest {

    @Nested
    class AdminCreationTest {
        String email;

        @BeforeEach
        void setUp() {
            email = "admin@order.com";
        }

        @Test
        void adminCreation_givenValidValues_thenAdminIsCreated() {
            Admin actual = new Admin(email);

            Assertions.assertThat(actual.getEmail()).isEqualTo(email);
        }
    }

    @Nested
    class AdminAuthorizationTest {
        Admin admin;

        @BeforeEach
        void setUp() {
            admin = new Admin("admin@order.com");
        }

        @Test
        void isAuthorized_givenCustomerRole_thenReturnTrue() {
            boolean actual = admin.isAuthorized(Role.CUSTOMER);

            Assertions.assertThat(actual).isTrue();
        }

        @Test
        void isAuthorized_givenAdminRole_thenReturnTrue() {
            boolean actual = admin.isAuthorized(Role.ADMIN);

            Assertions.assertThat(actual).isTrue();
        }

        @Test
        void isAuthorized_givenCustomerRoleAndCustomerId_thenReturnTrue() {
            boolean actual = admin.isAuthorized(Role.CUSTOMER, UUID.randomUUID().toString());

            Assertions.assertThat(actual).isTrue();
        }

    }

}