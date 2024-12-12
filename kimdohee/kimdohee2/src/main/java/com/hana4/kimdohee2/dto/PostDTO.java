package com.hana4.kimdohee2.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private Long id;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
//    @JsonBackReference
    @ToString.Exclude
    private String title;
    private String writerId; // User ID
    private String body;
}
