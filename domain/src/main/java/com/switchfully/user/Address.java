package com.switchfully.user;

public class Address {
    private final String street;
    private final String house;
    private final String town;
    private final String postCode;

    public Address(String street, String house, String town, String postCode) {
        this.street = street;
        this.house = house;
        this.town = town;
        this.postCode = postCode;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getTown() {
        return town;
    }

    public String getPostCode() {
        return postCode;
    }

}
