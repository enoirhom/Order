package com.switchfully.customer;

import com.switchfully.Customer;
import com.switchfully.CustomerRepository;
import com.switchfully.customer.dto.CreateCustomerDto;
import com.switchfully.customer.dto.CustomerDto;
import com.switchfully.customer.dto.CustomerDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoMapper customerDtoMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerDtoMapper customerDtoMapper) {
        this.customerRepository = customerRepository;
        this.customerDtoMapper = customerDtoMapper;
    }

    public CustomerDto addCustomer(CreateCustomerDto createCustomerDto) {
        Customer customerToAdd = customerDtoMapper.mapDtoToCustomer(createCustomerDto);
        customerRepository.addCustomer(customerToAdd);
        return customerDtoMapper.mapToDto(customerToAdd);
    }

    public CustomerDto getCustomerById(String id) {
        return customerDtoMapper.mapToDto(customerRepository.getCustomerById(id));
    }

}
