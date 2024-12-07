package com.hana4.kimdohee2.controller;

import com.hana4.kimdohee2.dto.PostDTO;
import com.hana4.kimdohee2.entity.Post;
import com.hana4.kimdohee2.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }
    @PostMapping("/posts")
    public ResponseEntity<Void> createPost(@RequestBody PostDTO post) {
        return postService.addPost(post);
//        Post post = postService.convertToPost(dto);
//        postRepository.save(post);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/{id}")
    public PostDTO getPost(@PathVariable("id") Long id) {
        System.out.println("id = " + id);
        return (PostDTO) postService.getPost(id);
    }
    @PatchMapping("/{id}")
    public PostDTO updatePost(@PathVariable("id") Long id, @RequestBody PostDTO post) {
        post.setId(id);
        return postService.modifyPost(post);
    }

    @DeleteMapping("/{id}")
    public PostDTO removePost(@PathVariable("id") Long id) {
        System.out.println("delete.id = " + id);
        return postService.removePost(id);
    }
}
