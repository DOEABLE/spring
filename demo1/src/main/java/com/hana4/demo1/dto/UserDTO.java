package com.hana4.demo1.dto;

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
    private String name;
    private short age;

    public void setName(String name) {

    }
}
