package com.hana4.demo1.dao;

import com.hana4.demo1.dto.PostDTO;

import java.util.List;

public interface PostDAO {
    List<PostDTO> findAll();

    PostDTO findById(String id);

    PostDTO insert(PostDTO post);

    PostDTO update(PostDTO post);

    PostDTO delete(String id);
}
