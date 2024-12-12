package com.hana4.kimdohee2.service;

import com.hana4.kimdohee2.dto.CommentDTO;
import com.hana4.kimdohee2.dto.PostDTO;
import com.hana4.kimdohee2.entity.User;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getAllComments();

    List<CommentDTO> getCommentsByPost(Long postId);

    CommentDTO getCommentById(Long id);

    CommentDTO addComment(User user, CommentDTO commentDTO);

    CommentDTO deleteComment(Long id);
    CommentDTO modifyComment(CommentDTO commentDTO);
}
