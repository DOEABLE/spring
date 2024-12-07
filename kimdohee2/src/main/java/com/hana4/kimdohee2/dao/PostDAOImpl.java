package com.hana4.kimdohee2.dao;

import com.hana4.kimdohee2.dto.PostDTO;
import com.hana4.kimdohee2.dto.PostMapper;
import com.hana4.kimdohee2.entity.Post;
import com.hana4.kimdohee2.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PostDAOImpl implements PostDAO{
    private final PostRepository repository;

    public PostDAOImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PostDTO> findAll() {
        List<Post> posts = repository.findAll();
        return posts.stream().map(PostMapper::toDTO).toList();
    }

    @Override
    public PostDTO findById(String id) {
        return null;
    }

    @Override
    public PostDTO insert(PostDTO post) {
        return null;
    }

    @Override
    public PostDTO update(PostDTO post) {
        return null;
    }

    @Override
    public PostDTO delete(String id) {
        return null;
    }
}
