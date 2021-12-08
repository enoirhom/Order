package com.switchfully.customer.dto;

import com.switchfully.user.Address;

public record CustomerDto(String id, String firstname, String lastname, String email, Address address, String phoneNumber) {
}
