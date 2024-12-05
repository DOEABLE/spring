package com.hana4.demo1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hana4.demo1.dao.PostDAO;
import com.hana4.demo1.dto.PostDTO;

@Service
public class PostServiceImpl implements PostService {

    private final PostDAO dao;

    public PostServiceImpl(PostDAO dao) {
        this.dao = dao;
    }


    @Override
    public List<PostDTO> getPosts() {
        return dao.findAll();
    }

    @Override
    public PostDTO addPost(PostDTO post) {
        return dao.insert(post);
    }

    @Override
    public PostDTO getPost(String id) {
        return dao.findById(id);
    }

    @Override
    public PostDTO modifyPost(PostDTO post) {
        return dao.update(post);
    }

    @Override
    public PostDTO removePost(String id) {
        return dao.delete(id);
    }
}