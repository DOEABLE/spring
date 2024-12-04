package com.hana4.demo1.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private String id;
    private String title;
    private String writer;
    private LocalDateTime createdate;
    private LocalDateTime workdate;
    private String body;
}
