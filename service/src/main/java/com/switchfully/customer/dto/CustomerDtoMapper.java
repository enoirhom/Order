package com.switchfully.customer.dto;

import com.switchfully.Customer;

public class CustomerDtoMapper {

    public CustomerDto mapToDto(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getFirstname(), customer.getLastname(), customer.getEmail(), customer.getAddress(), customer.getPhoneNumber());
    }

    public Customer mapDtoToCustomer(CreateCustomerDto createCustomerDto) {
        return new Customer(createCustomerDto.firstname(), createCustomerDto.lastname(), createCustomerDto.email(), createCustomerDto.address(), createCustomerDto.phoneNumber());
    }
}
