package com.hana4.demo1.service;



import com.hana4.demo1.dto.CodeDTO;

import java.util.List;

public interface CodeService {
    public CodeDTO addCode(CodeDTO dto);
    public CodeDTO getCode(int id);
    public List<CodeDTO> getCodes();
    public CodeDTO modifyCode(CodeDTO dto);
    public long removeCode(long id);

    CodeDTO findByName(Object codeName);
}
