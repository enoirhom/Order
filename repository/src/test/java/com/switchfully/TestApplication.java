package com.switchfully;

import com.switchfully.customer.CustomerRepository;
import com.switchfully.customer.CustomerRepositoryJpa;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {


    @Bean
    CustomerRepository customerRepository() {
        return new CustomerRepositoryJpa();
    }

}

