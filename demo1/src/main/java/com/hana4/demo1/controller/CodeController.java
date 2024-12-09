package com.hana4.demo1.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hana4.demo1.dto.CodeDTO;
import com.hana4.demo1.service.CodeService;

@RestController
@RequestMapping("/codes")
public class CodeController {

		CodeService codeService;

		public CodeController(CodeService codeService) {
				this.codeService = codeService;
		}

		@GetMapping
		public List<CodeDTO> getCodes() {
				return codeService.getCodes();
		}

		@GetMapping("/{id}")
		public CodeDTO getCode(@PathVariable("id") int id) {
				return codeService.getCode(id);
		}

		@PostMapping
		public CodeDTO addCode(@RequestBody CodeDTO dto) {
				return codeService.addCode(dto);
		}

		@PatchMapping("/{id}")
		public CodeDTO modifyCode(@PathVariable("id") int id, @RequestBody CodeDTO dto) {
				dto.setId(id);
				return codeService.modifyCode(dto);
		}

		@DeleteMapping("/{id}")
		public Long removeCode(@PathVariable("id") int id) {
				return codeService.removeCode(id);
		}
}
