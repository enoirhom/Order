package com.switchfully.security;


import com.switchfully.customer.CustomerRepository;
import com.switchfully.stub.CustomerRepositoryStub;
import com.switchfully.security.exception.UnauthorizedAccessException;
import com.switchfully.security.exception.UnknownUserException;
import com.switchfully.user.Address;
import com.switchfully.user.Customer;
import com.switchfully.user.Role;
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
        Address randomAddress = new Address("Customer Street", "8B", "Customer Town", 6666);
        knownCustomer = new Customer("Customer", "One", "customer@one.com", randomAddress,"046532165");
        unknownCustomer = new Customer("Unknown", "Random", "unknown@random.com", randomAddress,"046532165");
    }

    void setupRepository() {
        customerRepository = new CustomerRepositoryStub();
        customerRepository.addCustomer(knownCustomer);
    }

    @Test
    void validateAdminRole_givenAdminAuthorization_thenNoExceptionIsThrown() {
        String authorisation = generateAdminAuthorization();

        Assertions.assertThatNoException().isThrownBy(() -> securityService.validate(authorisation, Role.ADMIN));
    }

    @Test
    void validateAdminRole_givenAdminAuthorizationAndId_thenNoExceptionIsThrown() {
        String authorisation = generateAdminAuthorization();

        Assertions.assertThatNoException().isThrownBy(() -> securityService.validate(authorisation, Role.ADMIN, knownCustomer.getId().toString()));
    }

    @Test
    void validateCustomerRole_givenCustomerAuthorization_thenNoExceptionIsThrown() {
        String authorisation = generateKnownCustomerAuthorization();

        Assertions.assertThatNoException().isThrownBy(() -> securityService.validate(authorisation, Role.CUSTOMER));
    }

    @Test
    void validateCustomerRole_givenCustomerAuthorizationAndId_thenNoExceptionIsThrown() {
        String authorisation = generateKnownCustomerAuthorization();

        Assertions.assertThatNoException().isThrownBy(() -> securityService.validate(authorisation, Role.CUSTOMER, knownCustomer.getId().toString()));
    }

    @Test
    void validate_givenUnknownAuthorization_thenUnknownUserExceptionIsThrown() {
        String authorisation = generateUnknownCustomerAuthorization();

        Assertions.assertThatExceptionOfType(UnknownUserException.class).isThrownBy(() -> securityService.validate(authorisation, Role.CUSTOMER));
    }

    @Test
    void validate_givenUnknownAuthorizationAndId_thenUnknownUserExceptionIsThrown() {
        String authorisation = generateUnknownCustomerAuthorization();

        Assertions.assertThatExceptionOfType(UnknownUserException.class).isThrownBy(() -> securityService.validate(authorisation, Role.CUSTOMER, knownCustomer.getId().toString()));
    }

    @Test
    void validateAdminRole_givenCustomerAuthorization_thenUnauthorizedAccessException() {
        String authorisation = generateKnownCustomerAuthorization();

        Assertions.assertThatExceptionOfType(UnauthorizedAccessException.class).isThrownBy(() -> securityService.validate(authorisation, Role.ADMIN));
    }

    @Test
    void validateAdminRole_givenCustomerAuthorizationAndId_thenUnauthorizedAccessException() {
        String authorisation = generateKnownCustomerAuthorization();

        Assertions.assertThatExceptionOfType(UnauthorizedAccessException.class).isThrownBy(() -> securityService.validate(authorisation, Role.ADMIN, knownCustomer.getId().toString()));
    }

    @Test
    void validateAnyRole_givenNoAuthorization_thenUnknownUserExceptionIsThrown() {
        String authorisation = null;
        Assertions.assertThatExceptionOfType(UnknownUserException.class).isThrownBy(() -> securityService.validate(authorisation, Role.CUSTOMER));
        Assertions.assertThatExceptionOfType(UnknownUserException.class).isThrownBy(() -> securityService.validate(authorisation, Role.ADMIN));
    }

    @Test
    void validateAnyRole_givenNoAuthorizationAndId_thenUnknownUserExceptionIsThrown() {
        String authorisation = null;
        Assertions.assertThatExceptionOfType(UnknownUserException.class).isThrownBy(() -> securityService.validate(authorisation, Role.CUSTOMER, knownCustomer.getId().toString()));
        Assertions.assertThatExceptionOfType(UnknownUserException.class).isThrownBy(() -> securityService.validate(authorisation, Role.ADMIN, knownCustomer.getId().toString()));
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