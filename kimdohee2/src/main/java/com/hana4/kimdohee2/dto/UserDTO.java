package com.hana4.kimdohee2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class UserDTO {
    private String id;
    @Schema(description = "고객명", example = "Kim1")
    private String name;
    @Schema(description = "이메일", example = "Kim1@gmail.com")
    private String email;
}

