package com.hana4.demo1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CodeBaseDTO {
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
