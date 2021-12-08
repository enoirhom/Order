package com.switchfully.security;


import com.switchfully.user.Address;
import com.switchfully.user.Customer;
import com.switchfully.CustomerRepository;
import com.switchfully.user.Role;
import com.switchfully.security.exception.UnauthorizedAccessException;
import com.switchfully.security.exception.UnknownUserException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

class SecurityServiceTest {
    SecurityService securityService;
    CustomerRepository customerRepository;
    Customer knownCustomer;
    Customer unknownCustomer;

    @BeforeEach
    void setup() {
        setupCustomers();
        setupRepository();
        securityService = new SecurityService(customerRepository);
    }

    void setupCustomers() {
        Address randomAddress = new Address("Customer Street", "8B", "Customer Town", "6666");
        knownCustomer = new Customer("Customer", "One", "customer@one.com", randomAddress,"046532165");
        unknownCustomer = new Customer("Unknown", "Random", "unknown@random.com", randomAddress,"046532165");
    }

    void setupRepository() {
        customerRepository = new CustomerRepository();
        customerRepository.addCustomer(knownCustomer);
    }

    @Test
    void validateAdminRole_givenAdminAuthorization_thenNoExceptionIsThrown() {
        String authorisation = generateAdminAuthorization();

        Assertions.assertThatNoException().isThrownBy(() -> securityService.validate(authorisation, Role.ADMIN));
    }

    @Test
    void validateCustomerRole_givenCustomerAuthorization_thenNoExceptionIsThrown() {
        String authorisation = generateKnownCustomerAuthorization();

        Assertions.assertThatNoException().isThrownBy(() -> securityService.validate(authorisation, Role.CUSTOMER));
    }

    @Test
    void validate_givenUnknownAuthorization_thenUnknownUserExceptionIsThrown() {
        String authorisation = generateUnknownCustomerAuthorization();

        Assertions.assertThatExceptionOfType(UnknownUserException.class).isThrownBy(() -> securityService.validate(authorisation, Role.CUSTOMER));
    }

    @Test
    void validateAdminRole_givenCustomerAuthorization_thenUnauthorizedAccessException() {
        String authorisation = generateKnownCustomerAuthorization();

        Assertions.assertThatExceptionOfType(UnauthorizedAccessException.class).isThrownBy(() -> securityService.validate(authorisation, Role.ADMIN));
    }

    @Test
    void validateAnyRole_givenNoAuthorization_thenUnknownUserExceptionIsThrown() {
        String authorisation = null;
        Assertions.assertThatExceptionOfType(UnknownUserException.class).isThrownBy(() -> securityService.validate(authorisation, Role.CUSTOMER));
        Assertions.assertThatExceptionOfType(UnknownUserException.class).isThrownBy(() -> securityService.validate(authorisation, Role.ADMIN));
    }

    private String generateAdminAuthorization() {
        return generateBase64Authorization("admin@order.com", "");
    }

    private String generateKnownCustomerAuthorization() {
        return generateBase64Authorization(knownCustomer.getEmail(), "");
    }

    private String generateUnknownCustomerAuthorization() {
        return generateBase64Authorization(unknownCustomer.getEmail(), "");
    }

    private String generateBase64Authorization(String email, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((email + ":" + password).getBytes(StandardCharsets.UTF_8));
    }

}