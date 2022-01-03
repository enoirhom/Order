package com.switchfully.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private int zipCode;

    public Address(String street, String houseNumber, String city, int zipCode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.zipCode = zipCode;
    }

    protected Address() {

    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

    public int getZipCode() {
        return zipCode;
    }

}
