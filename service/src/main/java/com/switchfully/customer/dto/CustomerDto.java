package com.switchfully.customer.dto;

import com.switchfully.Address;

public record CustomerDto(String id, String firstname, String lastname, String email, Address address, String phoneNumber) {
}
