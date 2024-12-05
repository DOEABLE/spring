package com.hana4.demo1.dao;

import com.hana4.demo1.dto.PostDTO;
import com.hana4.demo1.dto.PostMapper;
import com.hana4.demo1.entity.Post;
import com.hana4.demo1.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PostDAOImpl implements PostDAO{
    private final PostRepository repository;

    public PostDAOImpl(PostRepository repository){
        this.repository = repository;
    }

    @Override
    public List<PostDTO> findAll() {
        List<Post> posts = repository.findAll();
        return posts.stream().map(PostMapper::toDTO).toList();
    }

    @Override
    public PostDTO findById(String id) {
        Optional<Post> post = repository.findById(id);
        return post.map(PostMapper::toDTO).orElse(null);
    }

    @Override
    public PostDTO insert(PostDTO post) {
        Post savedPost = repository.save(PostMapper.toPost(post));
        return PostMapper.toDTO(savedPost);
    }

    @Override
    public PostDTO update(PostDTO post) {
        Optional<Post> foundPost = repository.findById(post.getId());
        if(foundPost.isPresent()){
            Post toSavePost = foundPost.get();
            toSavePost.setTitle(post.getTitle());
            toSavePost.setWriter(post.getWriter());
            toSavePost.setBody(post.getBody());
            return PostMapper.toDTO(repository.save(toSavePost));
        }else{
            throw new IllegalStateException("Post Not Found!");
        }
    }

    @Override
    public PostDTO delete(String id) {
        Optional<Post> foundPost = repository.findById(id);
        if(foundPost.isPresent()){
            System.out.println("foundPost.get() = " + foundPost.get());
            repository.delete(foundPost.get());
            return PostMapper.toDTO(foundPost.get());
        }else{
            throw new IllegalStateException("Post Not Found!");
        }
    }
}
