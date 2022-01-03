package com.switchfully;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchfully.item.dto.CreateItemDto;
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
        CreateItemDto createItemDto = new CreateItemDto("n", "d", 10, 19.99);

        mockMvc.perform(post("/items")
                .header("Authorization", "Basic YWRtaW5Ab3JkZXIuY29tOg==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createItemDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(createItemDto.name()))
                .andExpect(jsonPath("$.description").value(createItemDto.description()))
                .andExpect(jsonPath("$.quantity").value(createItemDto.quantity()))
                .andExpect(jsonPath("$.price").value(createItemDto.price()));

    }

}