package com.hana4.kimdohee2.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    private Long id;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long postId;    // Post ID
    private String writerId; // User ID
    private String body;
}
