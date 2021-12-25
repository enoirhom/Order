package com.switchfully;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchfully.stock.dto.CreateStockItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {

    }

    @Test
    void addItem_givenCreateItemDto_thenReturnItemDtoJSON() throws Exception {
        CreateStockItemDto createStockItemDto = new CreateStockItemDto("n", "d", 10, 19.99);

        mockMvc.perform(post("/items")
                .header("Authorization", "Basic YWRtaW5Ab3JkZXIuY29tOg==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createStockItemDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(createStockItemDto.name()))
                .andExpect(jsonPath("$.description").value(createStockItemDto.description()))
                .andExpect(jsonPath("$.quantity").value(createStockItemDto.quantity()))
                .andExpect(jsonPath("$.price").value(createStockItemDto.price()));

    }

}