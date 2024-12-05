package com.hana4.demo1.dto;

import com.hana4.demo1.entity.Post;

public class PostMapper {
    public static PostDTO toDTO(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .writer(post.getWriter())
                .createdate(post.getCreatedate())
                .workdate(post.getWorkdate())
                .body(post.getBody())
                .build();
    }

    public static Post toPost(PostDTO dto) {
        return new Post(dto.getTitle(), dto.getWriter(), dto.getBody());
    }
}
