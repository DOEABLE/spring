package com.hana4.kimdohee2.dto;

import com.hana4.kimdohee2.entity.Post;
import com.hana4.kimdohee2.entity.User;

public class PostMapper {
    public static PostDTO toDTO(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .createAt(post.getCreateAt())
                .updateAt(post.getUpdateAt())
                .title(post.getTitle())
                .writerId(post.getWriter().getId())
                .body(post.getBody())
                .build();
    }

    public static Post toPost(PostDTO dto, User writer) {
        return new Post(dto.getTitle(),writer,dto.getBody());
    }
}
