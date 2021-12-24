package com.switchfully.user;

import java.util.UUID;

public class Customer implements Authorizable {
    private final String id;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final Address address;
    private final String phoneNumber;
    private final Role role;

    public Customer(String firstname, String lastname, String email, Address address, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = Role.CUSTOMER;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean isAuthorized(Role role) {
        return this.role.rank >= role.rank;
    }

    @Override
    public boolean isAuthorized(Role expectedRole, String userId) {
        return isAuthorized(expectedRole) && id.equals(userId);
    }
}
