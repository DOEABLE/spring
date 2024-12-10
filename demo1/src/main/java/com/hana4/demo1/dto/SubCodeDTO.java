package com.hana4.demo1.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder
public class SubCodeDTO extends CodeBaseDTO {
    private long id;
    private String value;

    @JsonBackReference
    @ToString.Exclude
    private CodeDTO code;

    public SubCodeDTO(String value) {
        this.value = value;
    }

    public SubCodeDTO(String value, CodeDTO code) {
        this.value = value;
        this.code = code;
    }
}
