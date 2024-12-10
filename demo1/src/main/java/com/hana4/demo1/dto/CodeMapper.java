package com.hana4.demo1.dto;

import java.util.List;
import java.util.Objects;

import com.hana4.demo1.entity.Code;
import com.hana4.demo1.entity.SubCode;

public class CodeMapper {
		public static CodeDTO toDTO(Code code) {
			if(Objects.isNull(code)){
				return null;
			}
				return CodeDTO.builder()
						.id(code.getId())
						.codeName(code.getCodeName())
						.createAt(code.getCreateAt())
						.updateAt(code.getUpdateAt())
						.build();


		}

		public static Code toEntity(CodeDTO dto) {
			if(Objects.isNull(dto)){
				return null;
			}
				Code code = new Code();
				code.setId(dto.getId());
				code.setCodeName(dto.getCodeName());
				if (!Objects.isNull(dto.getSubcodes())) {
						List<SubCode> subCodes = dto.getSubcodes().stream().map(SubCodeMapper::toEntity).toList();
						code.setSubcodes(subCodes);
				}

				return code;
		}
}
