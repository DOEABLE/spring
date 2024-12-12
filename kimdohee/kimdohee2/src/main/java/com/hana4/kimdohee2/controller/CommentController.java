package com.hana4.kimdohee2.controller;

import com.hana4.kimdohee2.dto.CommentDTO;
import com.hana4.kimdohee2.dto.PostDTO;
import com.hana4.kimdohee2.entity.User;
import com.hana4.kimdohee2.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentDTO> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public CommentDTO getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @GetMapping("/posts/{postId}")
    public List<CommentDTO> getCommentsByPost(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }

    @PostMapping("/posts/{postId}")
    public CommentDTO createComment(@PathVariable Long postId, @RequestBody CommentDTO commentDTO) {
        return commentService.addComment(User.builder().build(), commentDTO);
    }

    @DeleteMapping("/{id}")
    public CommentDTO deleteComment(@PathVariable("id") Long id) {
        return commentService.deleteComment(id);
    }
    @PatchMapping("/{id}")
    public CommentDTO updateComment(@PathVariable("id") Long id, @RequestBody CommentDTO commentDTO) {
        commentDTO.setId(id);
        return commentService.modifyComment(commentDTO);
    }
}
