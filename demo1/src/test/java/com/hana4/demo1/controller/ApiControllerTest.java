package com.hana4.demo1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hana4.demo1.dto.UserDTO;
import com.hana4.demo1.repository.JpaUserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class ApiControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    // @Autowired
    // WebApplicationContext context;

    @AfterAll
    void afterAll(@Autowired JpaUserRepository repository) {
        System.out.println("After Alllllllllllllllllllllll");
        repository.destroy();
    }

// @BeforeEach
// void beforeEach() {
// 	this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
// }

    @Test
    @DisplayName("/api/users - User list test")
    void getUserListTest() throws Exception {
        final String url = "/api/users";
        final ResultActions result = mockMvc.perform(
                get(url)
                // get(url).param("q", "")
        );

        List<UserDTO> list = Arrays.asList(
                new UserDTO(1L, "AA10", (short) 10),
                new UserDTO(2L, "AA11", (short) 11), new UserDTO(3L, "AA12", (short) 12));

        String reqStr = objectMapper.writeValueAsString(list);
        System.out.println("reqStr = " + reqStr);

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(reqStr));
    }

    @Test
    void addUserTest() throws Exception {
        final String url = "/api/users";
        final String name = "Hong";
        final short age = 33;
        final UserDTO dto = new UserDTO();
        dto.setName(name);
        dto.setAge(age);
        final String reqStr = objectMapper.writeValueAsString(dto);
        System.out.println("reqStr = " + reqStr);

        final ResultActions result = mockMvc.perform(
                post(url).contentType(MediaType.APPLICATION_JSON)
                        .content(reqStr)
        );

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value((int) age));

    }

}