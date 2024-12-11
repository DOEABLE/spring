package com.hana4.kimdohee2.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hana4.kimdohee2.controller.PostController;
import com.hana4.kimdohee2.dto.PostDTO;
import com.hana4.kimdohee2.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(PostController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper; // JSON 변환용

    @Autowired
    private PostService service;
    @Test
    void getPostTest() throws Exception {
        final Long ID = 1L;
        final LocalDateTime now = LocalDateTime.now();

        final PostDTO dto = new PostDTO(ID,now, now, "title", "writer",  "body");

        BDDMockito.given(service.getPost(ID)).willReturn(dto);

        final String url = "/posts/" + ID;
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.writer").value("writer"))
                .andDo(print());

    }
    @Test
    @DisplayName("Post 쓰기 테스트")
    @Order(1)
    public void createPostTest() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("title", "Test Post");
        request.put("content", "This is a test post.");
        request.put("writer", "TestUser");

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Post"))
                .andExpect(jsonPath("$.writer").value("TestUser"));
    }
//    @Test
//    @DisplayName("Post 수정 테스트")
//    public void updatePostTest() throws Exception {
//        Map<String, String> request = new HashMap<>();
//        request.put("title", "Updated Post");
//        request.put("content", "This is an updated test post.");
//
//        mockMvc.perform(put("/posts/{postId}", 1)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("Updated Post"));
//    }
//    @Test
//    @DisplayName("Post 삭제 테스트")
//    public void deletePostTest() throws Exception {
//        mockMvc.perform(delete("/posts/{postId}", 1))
//                .andExpect(status().isNoContent());
//    }
//    @Test
//    @DisplayName("Post 목록 보기 테스트")
//    public void listPostsTest() throws Exception {
//        mockMvc.perform(get("/posts"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isArray());
//    }
//
//    @Test
//    @DisplayName("Post 상세 보기 테스트")
//    public void getPostDetailTest() throws Exception {
//        mockMvc.perform(get("/posts/{postId}", 1))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1));
//    }
//    @Test
//    @DisplayName("댓글 쓰기 테스트")
//    public void createCommentTest() throws Exception {
//        Map<String, String> request = new HashMap<>();
//        request.put("content", "This is a test comment.");
//        request.put("writer", "CommentUser");
//
//        mockMvc.perform(post("/posts/{postId}/comments", 1)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.content").value("This is a test comment."));
//    }
//
//    @Test
//    @DisplayName("댓글 목록 보기 테스트")
//    public void listCommentsTest() throws Exception {
//        mockMvc.perform(get("/posts/{postId}/comments", 1))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isArray());
//    }
//
//    @Test
//    @DisplayName("댓글 수정 테스트")
//    public void updateCommentTest() throws Exception {
//        Map<String, String> request = new HashMap<>();
//        request.put("content", "Updated comment content.");
//
//        mockMvc.perform(put("/posts/{postId}/comments/{commentId}", 1, 1)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content").value("Updated comment content."));
//    }
//
//    @Test
//    @DisplayName("댓글 삭제 테스트")
//    public void deleteCommentTest() throws Exception {
//        mockMvc.perform(delete("/posts/{postId}/comments/{commentId}", 1, 1))
//                .andExpect(status().isNoContent());
//    }
}
