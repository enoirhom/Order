package com.switchfully.user;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer implements Authorizable {

    @Id
    @Column(name = "customer_id")
    private UUID id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Embedded
    private Address address;

    @Column(name = "phone_number")
    private String phoneNumber;

    public Customer(String firstname, String lastname, String email, Address address, String phoneNumber) {
        this.id = UUID.randomUUID();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    protected Customer() {

    }

    public UUID getId() {
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
        return Role.CUSTOMER.rank >= role.rank;
    }

    @Override
    public boolean isAuthorized(Role expectedRole, UUID userId) {
        return isAuthorized(expectedRole) && id.equals(userId);
    }
}
