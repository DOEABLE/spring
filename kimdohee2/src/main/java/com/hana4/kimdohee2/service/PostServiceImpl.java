package com.hana4.kimdohee2.service;

import com.hana4.kimdohee2.dao.PostDAO;
import com.hana4.kimdohee2.dto.PostDTO;
import com.hana4.kimdohee2.dto.PostMapper;
import com.hana4.kimdohee2.entity.Post;
import com.hana4.kimdohee2.entity.User;
import com.hana4.kimdohee2.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostDAO dao;
    private UserRepository userRepository;

    public PostServiceImpl(PostDAO dao) {
        this.dao = dao;
    }

    @Override
    public Object getPost(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> addPost(PostDTO post) {
        return null;
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

    public Post convertToPost(PostDTO dto) {
        User writer = userRepository.findById(dto.getWriterId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid writer ID: " + dto.getWriterId()));
        return PostMapper.toPost(dto, writer);
    }
}
