package com.hana4.demo1.service;

import com.hana4.demo1.dto.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getPosts();

    PostDTO addPost(PostDTO post);

    PostDTO getPost(String id);

    PostDTO modifyPost(PostDTO post);

    PostDTO removePost(String id);
}
