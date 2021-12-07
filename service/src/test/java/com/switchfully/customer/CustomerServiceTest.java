package com.switchfully.customer;

import com.switchfully.Address;
import com.switchfully.CustomerRepository;
import com.switchfully.customer.dto.CreateCustomerDto;
import com.switchfully.customer.dto.CustomerDto;
import com.switchfully.customer.dto.CustomerDtoMapper;
import com.switchfully.customer.exception.EmailAlreadyUsedException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CustomerServiceTest {
    CustomerService customerService;

    @BeforeEach
    void setup() {
        customerService = new CustomerService(new CustomerRepository(), new CustomerDtoMapper());
    }

    @Test
    void addCustomer_givenCreateCustomerDto_thenCustomerIsAdded() {
        CreateCustomerDto createCustomerDto =
                new CreateCustomerDto("Customer", "One", "customer@one.com",
                        new Address("Customer Street", "8B", "Customer Town", "6666"), "048653216");

        CustomerDto expected = customerService.addCustomer(createCustomerDto);
        CustomerDto actual = customerService.getCustomerById(expected.id());

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void addCustomer_givenCreateCustomDtoWithAlreadyUsedEmail_thenEmailAlreadyUsedExceptionIsThrown() {
        CreateCustomerDto createCustomerDto1 =
                new CreateCustomerDto("Customer", "One", "customer@one.com",
                        new Address("Customer Street", "8B", "Customer Town", "6666"), "048653216");
        customerService.addCustomer(createCustomerDto1);

        CreateCustomerDto createCustomerDto2 =
                new CreateCustomerDto("Customer", "Two", "customer@one.com",
                        new Address("Customer Street", "8B", "Customer Town", "6666"), "048653216");

        Assertions.assertThatExceptionOfType(EmailAlreadyUsedException.class).isThrownBy(() -> customerService.addCustomer(createCustomerDto2));
    }

    @Test
    void getAllCustomers_thenReturnsAllCustomersDto() {
        List<CustomerDto> expected = add3Customers();

        List<CustomerDto> actual = customerService.getAllCustomers();

        actual.forEach(customerDto -> Assertions.assertThat(customerDto).isIn(expected));
    }

    private List<CustomerDto> add3Customers() {
        List<CustomerDto> customerDtos = new ArrayList<>();

        Address address = new Address("Customer Street", "8B", "Customer Town", "6666");
        customerDtos.add(customerService.addCustomer(new CreateCustomerDto("Customer", "One", "customer@one.com", address, "048653216")));
        customerDtos.add(customerService.addCustomer(new CreateCustomerDto("Customer", "Two", "customer@two.com", address, "048653216")));
        customerDtos.add(customerService.addCustomer(new CreateCustomerDto("Customer", "Three", "customer@three.com", address, "048653216")));

        return customerDtos;
    }
}