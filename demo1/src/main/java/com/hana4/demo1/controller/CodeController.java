package com.hana4.demo1.controller;

import com.hana4.demo1.dto.CodeDTO;
import com.hana4.demo1.service.CodeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/codes")
public class CodeController {

    CodeService codeService;
    private final
    @PostMapping
    public CodeDTO addCode(@RequestBody CodeDTO dto) {
        return codeService.addCode(dto);

    }
    @PatchMapping("/{id}")
    public CodeDTO modifyCode(@PathVariable("id") int id, @RequestBody CodeDTO dto){
        dto.setId(id);
        return codeService.modifyCode(dto);
    }
    @DeleteMapping("/{id}")
    public CodeDTO removeCode() throws Exception{
        List<CodeDTO>
    }
}
