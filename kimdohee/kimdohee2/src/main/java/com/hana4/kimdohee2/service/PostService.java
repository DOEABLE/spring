package com.hana4.kimdohee2.service;

import com.hana4.kimdohee2.dto.PostDTO;
import com.hana4.kimdohee2.entity.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    Object getPost(Long id);

    PostDTO addPost(PostDTO postDTO);

    PostDTO modifyPost(PostDTO postDTO);

    PostDTO removePost(Long id);

    List<PostDTO> getAllPosts();

}
