package com.switchfully.user;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(house, address.house) && Objects.equals(town, address.town) && Objects.equals(postCode, address.postCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, house, town, postCode);
    }
}
