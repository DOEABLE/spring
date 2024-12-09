package com.hana4.kimdohee2.repository;

import com.hana4.kimdohee2.entity.Comment;
import com.hana4.kimdohee2.entity.Post;
import com.hana4.kimdohee2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 모든 댓글 조회 (GET /comments)
    List<Comment> findAll();

    // 특정 댓글 조회 (GET /comments/{id})
    Optional<Comment> findById(Long id);
    Comment save(Comment comment);

    // 특정 게시글의 모든 댓글 조회 (GET /posts/{postId}/comments)
    List<Comment> findByPostId(Long postId);

    void deleteById(Long id);

    //게시물 별 댓글 조회
    List<Comment> findByPost(Post post);
    //특정 작성자의 댓글 목록 조회
    List<Comment> findByWriter(Post post);
}
