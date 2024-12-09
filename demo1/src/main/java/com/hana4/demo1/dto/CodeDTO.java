package com.hana4.demo1.dto;

import com.hana4.demo1.entity.CodeInfo;
import com.hana4.demo1.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CodeDTO extends CodeBaseDTO {
    private int id;
    private String codeName;
//    private CodeInfo codeInfo;
    private List<SubCodeDTO> subcodes;
//    private List<User> codeUsers = new ArrayList<>();

    public CodeDTO(String codeName){
        this.codeName = codeName;
    }
}
