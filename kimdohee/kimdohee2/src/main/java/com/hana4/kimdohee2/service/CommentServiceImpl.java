package com.hana4.kimdohee2.service;

import com.hana4.kimdohee2.dto.CommentDTO;

import com.hana4.kimdohee2.dto.PostDTO;
import com.hana4.kimdohee2.dto.PostMapper;
import com.hana4.kimdohee2.entity.Comment;
import com.hana4.kimdohee2.entity.Post;
import com.hana4.kimdohee2.entity.User;
import com.hana4.kimdohee2.repository.CommentRepository;
import com.hana4.kimdohee2.repository.PostRepository;
import com.hana4.kimdohee2.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        return convertToDTO(comment);
    }

    // 댓글 추가
    @Override
    public CommentDTO addComment(User user, CommentDTO commentDTO) {
        // 댓글이 속한 게시글 조회
        Post post = postRepository.findById(commentDTO.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + commentDTO.getPostId()));

        User writer = userRepository.findById(commentDTO.getWriterId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + commentDTO.getWriterId()));

        // DTO를 Comment 엔티티로 변환
        Comment comment = new Comment(commentDTO.getBody(), post, user);
        comment.setPost(post); // 연관 관계 설정
        comment.setWriter(writer); // 연관 관계 설정
        comment.setBody(commentDTO.getBody());
        comment.setCreateAt(LocalDateTime.now());
        comment.setUpdateAt(LocalDateTime.now());

        // 댓글 저장
        Comment savedComment = commentRepository.save(comment);

        // 저장된 엔티티를 DTO로 변환하여 반환
        return convertToDTO(savedComment);
    }

    private Comment convertToEntity(CommentDTO commentDTO) {
        Post post = postRepository.findById(commentDTO.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + commentDTO.getPostId()));

        User user = userRepository.findById(commentDTO.getWriterId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + commentDTO.getWriterId()));
        return new Comment(
                commentDTO.getBody(),
                post,
                user
        );
    }

    @Override
    @Transactional
    public CommentDTO deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("not found comment!"));

        commentRepository.delete(comment);
        return convertToDTO(comment);

    }

    @Override
    public CommentDTO modifyComment(CommentDTO commentDTO) {
        Comment existingComment = commentRepository.findById(commentDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        existingComment.setBody(commentDTO.getBody());

        Comment updatedComment = commentRepository.save(existingComment);
        return convertToDTO(updatedComment);
    }

    private CommentDTO convertToDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getCreateAt(),
                comment.getUpdateAt(),
                comment.getPost().getId(),   // Post ID
                comment.getWriter().getId(), // User ID
                comment.getBody()
        );
    }
}
