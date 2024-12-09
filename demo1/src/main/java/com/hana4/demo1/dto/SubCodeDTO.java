package com.hana4.demo1.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
public class SubCodeDTO {
    private long id;
    private String value;
    private CodeDTO code;
}
