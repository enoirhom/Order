package com.switchfully.customer;

import com.switchfully.Address;
import com.switchfully.CustomerRepository;
import com.switchfully.customer.dto.CreateCustomerDto;
import com.switchfully.customer.dto.CustomerDto;
import com.switchfully.customer.dto.CustomerDtoMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerServiceTest {
    CustomerService customerService;

    @BeforeEach
    void setup() {
        customerService = new CustomerService(new CustomerRepository(), new CustomerDtoMapper());
    }

    @Test
    void addCustomer_givenCreateCustomerDto_thenCustomerIsAdded() {
        CreateCustomerDto createCustomerDto =
                new CreateCustomerDto("Jon", "Snow", "jon@snow.com",
                        new Address("Castle Street", "8B", "Winterfell", "6666"), "048653216");

        CustomerDto customerDto = customerService.addCustomer(createCustomerDto);
        CustomerDto actual = customerService.getCustomerById(customerDto.id());


        Assertions.assertThat(actual).isEqualTo(customerDto);
    }
}