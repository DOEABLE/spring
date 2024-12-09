package com.hana4.demo1.service;

import com.hana4.demo1.dto.CodeDTO;
import com.hana4.demo1.dto.CodeMapper;
import com.hana4.demo1.dto.SubCodeMapper;
import com.hana4.demo1.repository.CodeRepository;

import java.util.List;

public class CodeServiceImpl implements CodeService{
    private CodeRepository repository;

    @Override
    public CodeDTO addCode(CodeDTO dto) {
        return CodeMapper.toDTO(repository.save(CodeMapper.toEntity(dto)));
    }
    @Override
    public CodeDTO getCode(int id){
        return CodeMapper.toDTO(repository.findById(id).orElseThrow());
    }

    @Override
    public List<CodeDTO> getCodes() {
        return repository.findAll().stream()
                .map(CodeMapper::toDTO);
    }

    @Override
    public CodeDTO modifyCode(CodeDTO dto) {
        return null;
    }

    @Override
    public long removeCode(CodeDTO dto) {
        return 0;
    }
}
