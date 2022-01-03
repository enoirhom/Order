package com.switchfully.customer.dto;

import com.switchfully.user.Address;

import java.util.UUID;

public record CustomerDto(UUID id, String firstname, String lastname, String email, Address address, String phoneNumber) {
}
