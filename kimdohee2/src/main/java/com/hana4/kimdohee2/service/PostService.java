package com.hana4.kimdohee2.service;

import com.hana4.kimdohee2.dto.PostDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    Object getPost(Long id);

    ResponseEntity<Void> addPost(PostDTO post);

    PostDTO modifyPost(PostDTO post);

    PostDTO removePost(Long id);

    List<PostDTO> getAllPosts();
}
