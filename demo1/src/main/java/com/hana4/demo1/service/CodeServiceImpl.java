package com.hana4.demo1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hana4.demo1.dto.CodeDTO;
import com.hana4.demo1.dto.CodeMapper;
import com.hana4.demo1.entity.Code;
import com.hana4.demo1.repository.CodeRepository;

@Service
public class CodeServiceImpl implements CodeService {
		private CodeRepository repository;

		@Override
		public CodeDTO addCode(CodeDTO dto) {
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
}
