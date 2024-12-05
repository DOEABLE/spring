package com.hana4.demo1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "age")
@EqualsAndHashCode
@Builder
public class UserDTO {
    private Long id;

    @Schema(description = "고객명", example = "홍길동")
    private String name;

    @Schema(description = "나이", example = "33")
    private short age;
}
