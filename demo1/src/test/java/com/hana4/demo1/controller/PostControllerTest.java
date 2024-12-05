package com.hana4.demo1.controller;


import com.hana4.demo1.dto.PostDTO;
import com.hana4.demo1.service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PostController.class)
    public class PostControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private PostService service;

        @Test
        void getPostTest() throws Exception {
            final String ID = "XX";
            final LocalDateTime now = LocalDateTime.now();

            final PostDTO dto = new PostDTO(ID, "title", "writer", now, now, "body");

            BDDMockito.given(service.getPost(ID)).willReturn(dto);

            final String url = "/posts/" + ID;
            mockMvc.perform(get(url))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.title").value("title"))
                    .andExpect(jsonPath("$.writer").value("writer"))
                    .andDo(print());

        }
    }
