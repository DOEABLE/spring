package com.hana4.kimdohee2.service;

import com.hana4.kimdohee2.dao.PostDAO;
import com.hana4.kimdohee2.dto.PostDTO;
import com.hana4.kimdohee2.dto.PostMapper;
import com.hana4.kimdohee2.entity.Post;
import com.hana4.kimdohee2.entity.User;
import com.hana4.kimdohee2.repository.PostRepository;
import com.hana4.kimdohee2.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Object getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        return PostMapper.toDTO(post);
    }

    @Override
    public PostDTO addPost(PostDTO postDTO) {
        User writer = userRepository.findById(String.valueOf(UUID.fromString(postDTO.getWriterId())))
                .orElseThrow(() -> new IllegalArgumentException("Writer not found"));

        Post post = Post.builder()
                .title(postDTO.getTitle())
                .writer(writer)
                .body(postDTO.getBody())
                .build();

        Post savedPost = postRepository.save(post);

        return PostMapper.toDTO(savedPost);
    }

    @Override
    public PostDTO modifyPost(PostDTO postDTO) {
        Post existingPost = postRepository.findById(postDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        existingPost.setTitle(postDTO.getTitle());
        existingPost.setBody(postDTO.getBody());

        Post updatedPost = postRepository.save(existingPost);
        return PostMapper.toDTO(updatedPost);
    }

    @Override
    public PostDTO removePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        postRepository.delete(post);
        return PostMapper.toDTO(post);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostMapper::toDTO).toList();
    }
}
