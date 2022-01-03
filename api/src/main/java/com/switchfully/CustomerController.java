package com.switchfully;

import com.switchfully.customer.CustomerService;
import com.switchfully.customer.dto.CreateCustomerDto;
import com.switchfully.customer.dto.CustomerDto;
import com.switchfully.security.SecurityService;
import com.switchfully.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping(path = "{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CustomerDto getCustomerById(@PathVariable UUID id, @RequestHeader String authorization) {
        securityService.validate(authorization, Role.ADMIN);
        return customerService.getCustomerDtoById(id);
    }

}
