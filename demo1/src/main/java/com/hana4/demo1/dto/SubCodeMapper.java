package com.hana4.demo1.dto;

import com.hana4.demo1.entity.Code;
import com.hana4.demo1.entity.SubCode;

import java.util.List;

public class SubCodeMapper {
    public static SubCodeDTO toDTO(SubCode subCode) {
        return SubCodeDTO.builder()
                .id(subCode.getId())
                .value(subCode.getValue())
                .code(CodeMapper.toDTO(subCode.getCode()))
                .build();
    }

    public static SubCode toEntity(SubCodeDTO dto) {
        return new SubCode(dto.getId(), dto.getValue(), CodeMapper.toEntity(dto.getCode()));
    }
}
