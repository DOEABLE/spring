package com.hana4.demo1.controller;

import com.hana4.demo1.dto.CodeDTO;
import com.hana4.demo1.dto.SubCodeDTO;
import com.hana4.demo1.service.CodeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * SubCodeArea
     */
    @PostMapping("/{id}/subCodes")
    public SubCodeDTO addSubCode(@PathVariable("id") int codeId, @RequestBody SubCodeDTO subCodeDTO) {
        CodeDTO codeDTO = codeService.getCode(codeId);
        subCodeDTO.setCode(codeDTO);
        return codeService.addSubCode(subCodeDTO);
    }

    @GetMapping("/{id}/subcodes")
    public List<SubCodeDTO> getSubCodes(@PathVariable("id") int codeId) {
        return codeService.getSubCodes(codeId);
    }

    @PatchMapping({"/{id}/subcodes/{sid}", "/subcodes/{sid}"})
    public SubCodeDTO modifySubCode(@PathVariable("sid") long subCodeId, @RequestBody SubCodeDTO dto) {
        return codeService.modifySubCode(dto);
    }

    @DeleteMapping(value = {"/{id}/subcodes/{sid}", "/subcodes/{sid}"})
    public Long removeSubCode(@PathVariable("id") int codeId, @PathVariable("sid") long subCodeId) {
        return codeService.removeSubCode(subCodeId);
    }
}
