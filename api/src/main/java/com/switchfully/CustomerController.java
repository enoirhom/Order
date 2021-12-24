package com.switchfully;

import com.switchfully.customer.CustomerService;
import com.switchfully.customer.dto.CreateCustomerDto;
import com.switchfully.customer.dto.CustomerDto;
import com.switchfully.security.SecurityService;
import com.switchfully.user.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final SecurityService securityService;

    @Autowired
    public CustomerController(CustomerService customerService, SecurityService securityService) {
        this.customerService = customerService;
        this.securityService = securityService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    CustomerDto addCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
        return customerService.addCustomer(createCustomerDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<CustomerDto> getAllCustomers(@RequestHeader(value = "authorization", required = false) String authorization) {
        securityService.validate(authorization, Role.ADMIN);
        return customerService.getAllCustomersDto();
    }

}
