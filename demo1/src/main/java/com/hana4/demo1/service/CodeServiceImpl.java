package com.hana4.demo1.service;

import com.hana4.demo1.dto.CodeDTO;
import com.hana4.demo1.dto.CodeMapper;
import com.hana4.demo1.dto.SubCodeDTO;
import com.hana4.demo1.dto.SubCodeMapper;
import com.hana4.demo1.entity.Code;
import com.hana4.demo1.entity.SubCode;
import com.hana4.demo1.repository.CodeRepository;
import com.hana4.demo1.repository.SubCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CodeServiceImpl implements CodeService {
    private final CodeRepository repository;
    private final SubCodeRepository subCodeRepository;

    public CodeServiceImpl(CodeRepository repository, SubCodeRepository subCodeRepository) {
        this.repository = repository;
        this.subCodeRepository = subCodeRepository;
    }

    @Override
    public CodeDTO addCode(CodeDTO dto) {
        Code code = CodeMapper.toEntity(dto);
        if (Objects.isNull(dto) || Objects.isNull(code)) {
            return null;
        }

        repository.save(code);
        for (SubCode subCode : code.getSubcodes()) {
            subCode.setCode(code);
            subCodeRepository.save(subCode);
        }
        repository.save(code);
        return CodeMapper.toDTO(repository.save(CodeMapper.toEntity(dto)));
    }

    @Override
    public CodeDTO getCode(int id) {
        return CodeMapper.toDTO(repository.findById(id).orElseThrow());
    }

    @Override
    public CodeDTO findByName(String codeName) {
        return CodeMapper.toDTO(repository.findByCodeName(codeName).orElseThrow());

    }

    @Override
    public List<CodeDTO> getCodes() {
        return repository.findAll().stream()
                .map(CodeMapper::toDTO).toList();
    }

    @Override
    public CodeDTO modifyCode(CodeDTO dto) {
        Code code = repository.findById(dto.getId()).orElseThrow();
        code.setCodeName(dto.getCodeName());
        return CodeMapper.toDTO(repository.save(code));
    }

    @Override
    public long removeCode(int id) {
        repository.deleteById(id);
        return repository.count();
    }

    @Override
    public SubCodeDTO addSubCode(SubCodeDTO subCodeDTO) {
        return SubCodeMapper.toDTO(subCodeRepository.save(SubCodeMapper.toEntity(subCodeDTO)));
    }

    @Override
    public List<SubCodeDTO> getSubCodes(int codeId) {
        Code code = repository.findById(codeId).orElseThrow();
        return code.getSubcodes().stream().map(SubCodeMapper::toDTO).toList();//subCodeDTO로 바뀌어서 나옴.
    }

    @Override
    public List<CodeDTO> getCodesHaveSubCodes() {
        return repository.findBySubcodesIsNotEmpty().stream().map(CodeMapper::toDTO).toList();
    }

    @Override
    public SubCodeDTO modifySubCode(SubCodeDTO dto) {
        SubCode subCode = subCodeRepository.findById(dto.getId())
                .orElseThrow();
        subCode.setValue(dto.getValue());
        subCodeRepository.save(subCode);
        return SubCodeMapper.toDTO(subCode);
    }

    @Override
    public Long removeSubCode(long subCodeId) {
        SubCode subCode = subCodeRepository.findById(subCodeId).orElseThrow();
        Code code = repository.findById(subCode.getCode().getId()).orElseThrow();

        final boolean didRemove = code.getSubcodes().remove(subCode);
        // subCode.setCode(null);

        subCodeRepository.delete(subCode);
        // repository.save(code);

        return didRemove ? 1L : 0L;
    }


}
