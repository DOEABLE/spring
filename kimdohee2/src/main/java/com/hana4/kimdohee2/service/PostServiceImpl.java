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
        return null;
    }

    @Override
    public PostDTO addPost(PostDTO postDTO) {
        User writer = userRepository.findById(UUID.fromString(postDTO.getWriterId()))
                .orElseThrow(() -> new IllegalArgumentException("Writer not found"));

        Post post = PostMapper.toPost(postDTO);

        Post savedPost = postRepository.save(post);

        return PostMapper.toDTO(savedPost);
    }

    @Override
    public PostDTO modifyPost(PostDTO post) {
        return null;
    }

    @Override
    public PostDTO removePost(Long id) {
        return null;
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return null;
    }
}
