package com.switchfully;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchfully.customer.dto.CreateCustomerDto;
import com.switchfully.customer.dto.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

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

    @Test
    void getCustomerById_givenCustomerId_thenReturnCustomerDtoJSON() throws Exception {
        CreateCustomerDto createCustomerDto = new CreateCustomerDto("f", "l", "em", null, "p");

        String response = mockMvc.perform(post("/customers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createCustomerDto)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        CustomerDto customerDtoResponse = objectMapper.readValue(response, CustomerDto.class);

        mockMvc.perform(get("/customers/" + customerDtoResponse.id())
                .header("Authorization", "Basic YWRtaW5Ab3JkZXIuY29tOg==")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerDtoResponse.id()))
                .andExpect(jsonPath("$.firstname").value(createCustomerDto.firstname()))
                .andExpect(jsonPath("$.lastname").value(createCustomerDto.lastname()))
                .andExpect(jsonPath("$.email").value(createCustomerDto.email()))
                .andExpect(jsonPath("$.phoneNumber").value(createCustomerDto.phoneNumber()));
    }


}