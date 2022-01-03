package com.switchfully.customer;

import com.switchfully.customer.dto.CreateCustomerDto;
import com.switchfully.customer.dto.CustomerDto;
import com.switchfully.customer.dto.CustomerDtoMapper;
import com.switchfully.customer.exception.EmailAlreadyUsedException;
import com.switchfully.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoMapper customerDtoMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerDtoMapper customerDtoMapper) {
        this.customerRepository = customerRepository;
        this.customerDtoMapper = customerDtoMapper;
    }

    public CustomerDto addCustomer(CreateCustomerDto createCustomerDto) {
        if (customerRepository.findCustomerByEmail(createCustomerDto.email()).isPresent()) {
            throw new EmailAlreadyUsedException(createCustomerDto.email() + " is already used.");
        }

        Customer customerToAdd = customerDtoMapper.mapDtoToCustomer(createCustomerDto);
        customerRepository.addCustomer(customerToAdd);
        return customerDtoMapper.mapToDto(customerToAdd);
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findCustomerById(UUID.fromString(id));
    }

    public CustomerDto getCustomerDtoById(String id) {
        return customerDtoMapper.mapToDto(getCustomerById(id));
    }

    public List<CustomerDto> getAllCustomersDto() {
        return customerRepository.getAllCustomers()
                .stream()
                .map(customerDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
