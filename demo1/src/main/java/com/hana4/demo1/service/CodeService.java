package com.hana4.demo1.service;

import com.hana4.demo1.dto.CodeDTO;
import com.hana4.demo1.dto.SubCodeDTO;

import java.util.List;

public interface CodeService {
    public CodeDTO addCode(CodeDTO dto);

    public CodeDTO getCode(int id);

    public CodeDTO findByName(String codeName);

    public List<CodeDTO> getCodes();

    public CodeDTO modifyCode(CodeDTO dto);

    public long removeCode(int id);

    SubCodeDTO addSubCode(SubCodeDTO subCodeDTO);

    List<SubCodeDTO> getSubCodes(int codeId);

    List<CodeDTO> getCodesHaveSubCodes();

    SubCodeDTO modifySubCode(SubCodeDTO dto);

    Long removeSubCode(long subCodeId);
}
