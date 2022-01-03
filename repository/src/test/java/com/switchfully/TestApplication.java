package com.switchfully;

import com.switchfully.customer.CustomerRepository;
import com.switchfully.customer.CustomerRepositoryJpa;
import com.switchfully.item.ItemRepository;
import com.switchfully.item.ItemRepositoryJpa;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {


    @Bean
    CustomerRepository customerRepository() {
        return new CustomerRepositoryJpa();
    }

    @Bean
    ItemRepository itemRepository() {
        return new ItemRepositoryJpa();
    }

}

