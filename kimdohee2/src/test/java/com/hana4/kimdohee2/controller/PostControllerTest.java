package com.hana4.kimdohee2.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hana4.kimdohee2.controller.PostController;
import com.hana4.kimdohee2.dto.PostDTO;
import com.hana4.kimdohee2.entity.Post;
import com.hana4.kimdohee2.entity.User;
import com.hana4.kimdohee2.repository.PostRepository;
import com.hana4.kimdohee2.repository.UserRepository;
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
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(PostController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper; // JSON 변환용

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    @Order(2)
    void getPostTest() throws Exception {
        // 이미 존재하는 USER와 POST 데이터 준비
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new IllegalStateException("USER 테이블에 데이터가 없습니다.");
        }
        User writer = users.get(0);

        Post post = Post.builder()
                .title("Sample Post")
                .body("This is a sample post.")
                .writer(writer)
                .build();
        postRepository.save(post);

        // MockMvc를 사용한 GET 요청 및 검증
        mockMvc.perform(get("/posts/{id}", post.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample Post"))
                .andExpect(jsonPath("$.body").value("This is a sample post."))
                .andExpect(jsonPath("$.writerId").value(writer.getId())) // 작성자 ID 확인
                .andDo(print());
    }

    @Test
    @DisplayName("Post 쓰기 테스트")
    @Order(1)
    public void createPostTest() throws Exception {
        // 이미 존재하는 USER 테이블에서 ID 가져오기
        List<User> users = userRepository.findAll();
        System.out.println(users);
        if (users.isEmpty()) {
            throw new IllegalStateException("USER 테이블에 데이터가 없습니다.");
        }
        User writer = users.get(0); // 첫 번째 User 선택 (또는 조건에 맞는 User 선택)
        Map<String, String> request = new HashMap<>();
        request.put("title", "Test Post");
        request.put("body", "This is a test post.");
        request.put("writerId", writer.getId());

        mockMvc.perform(post("/posts/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Post"))
                .andExpect(jsonPath("$.body").value("This is a test post."))
                .andExpect(jsonPath("$.writerId").value(writer.getId()))
                .andDo(print());

    }

    @Test
    @DisplayName("Post 수정 테스트")
    @Order(3)
    public void updatePostTest() throws Exception {
        // 이미 존재하는 USER와 POST 데이터 준비
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new IllegalStateException("USER 테이블에 데이터가 없습니다.");
        }
        User writer = users.get(0);

        Post post = Post.builder()
                .title("Original Post")
                .body("Original body content.")
                .writer(writer)
                .build();
        postRepository.save(post);

        // 수정 요청 데이터 준비
        Map<String, String> updatedRequest = new HashMap<>();
        updatedRequest.put("title", "Updated Post Title");
        updatedRequest.put("body", "Updated body content.");

        // MockMvc를 사용한 PATCH 요청 및 검증
        mockMvc.perform(patch("/posts/{id}", post.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Post Title"))
                .andExpect(jsonPath("$.body").value("Updated body content."))
                .andExpect(jsonPath("$.writerId").value(writer.getId())) // 작성자 ID 확인
                .andDo(print());
//        Map<String, String> request = new HashMap<>();
//        request.put("title", "Test Post");
//        request.put("body", "This is a test post.");
//
//        mockMvc.perform(patch("/posts/{postId}", 1)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("Test Post"))
//                .andExpect(jsonPath("$.body").value("This is a test post."))
//                .andDo(print());
    }

    @Test
    @DisplayName("Post 삭제 테스트")
    @Order(4)
    public void deletePostTest() throws Exception {
        // 이미 존재하는 USER와 POST 데이터 준비
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new IllegalStateException("USER 테이블에 데이터가 없습니다.");
        }
        User writer = users.get(0);

        Post post = Post.builder()
                .title("Post to be deleted")
                .body("This post will be deleted.")
                .writer(writer)
                .build();
        postRepository.save(post);

        // MockMvc를 사용한 DELETE 요청 및 검증
        mockMvc.perform(delete("/posts/{id}", post.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Post to be deleted"))
                .andExpect(jsonPath("$.body").value("This post will be deleted."))
                .andExpect(jsonPath("$.writerId").value(writer.getId())) // 작성자 ID 확인
                .andDo(print());
//        mockMvc.perform(delete("/posts/{postId}", 1))
//                .andExpect(status().isNoContent());
    }
    @Test
    @DisplayName("Post 목록 보기 테스트")
    @Order(5)
    public void listPostsTest() throws Exception {
        // 이미 존재하는 USER 데이터 준비
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new IllegalStateException("USER 테이블에 데이터가 없습니다.");
        }
        User writer = users.get(0);

        // 기존 POST 데이터 수 확인
        long initialPostCount = postRepository.count();

        // 새로운 POST 데이터 준비
        Post post1 = Post.builder()
                .title("First Post")
                .body("Content of the first post.")
                .writer(writer)
                .build();
        postRepository.save(post1);

        Post post2 = Post.builder()
                .title("Second Post")
                .body("Content of the second post.")
                .writer(writer)
                .build();
        postRepository.save(post2);

        Post post3 = Post.builder()
                .title("Third Post")
                .body("Content of the third post.")
                .writer(writer)
                .build();
        postRepository.save(post3);

        // MockMvc를 사용한 GET 요청 및 검증
        mockMvc.perform(get("/posts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize((int) (initialPostCount + 3)))) // 총 POST 수 확인
                .andExpect(jsonPath("$[?(@.title == 'First Post')].body").value("Content of the first post."))
                .andExpect(jsonPath("$[?(@.title == 'Second Post')].body").value("Content of the second post."))
                .andExpect(jsonPath("$[?(@.title == 'Third Post')].body").value("Content of the third post."))
                .andDo(print());
//        mockMvc.perform(get("/posts"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isArray());
    }
//
    @Test
    @DisplayName("Post 상세 보기 테스트")
    @Order(6)
    public void getPostDetailTest() throws Exception {
        mockMvc.perform(get("/posts/{postId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
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

