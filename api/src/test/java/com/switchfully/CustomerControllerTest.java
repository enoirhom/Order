package com.switchfully;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchfully.customer.dto.CreateCustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {

    }

    @Test
    void addCustomer_givenCreateCustomerDto_thenReturnCustomerDtoJSON() throws Exception {
        CreateCustomerDto createCustomerDto = new CreateCustomerDto("f", "l", "e", null, "p");

        mockMvc.perform(post("/customers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createCustomerDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname").value(createCustomerDto.firstname()))
                .andExpect(jsonPath("$.lastname").value(createCustomerDto.lastname()))
                .andExpect(jsonPath("$.email").value(createCustomerDto.email()))
                .andExpect(jsonPath("$.phoneNumber").value(createCustomerDto.phoneNumber()));
    }


}