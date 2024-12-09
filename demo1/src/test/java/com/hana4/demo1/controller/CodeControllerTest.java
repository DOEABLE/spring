package com.hana4.demo1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hana4.demo1.dto.CodeDTO;
import com.hana4.demo1.repository.CodeRepository;
import com.hana4.demo1.service.CodeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CodeControllerTest {
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    CodeService codeService;

    private final List<CodeDTO> sampleCodes = new ArrayList<>();
    @BeforeAll
    void BeforeAll(@Autowired CodeRepository codeRepository){
        System.out.println("CodeControllerTest.BeforeAll --");
        codeRepository.deleteAll();;
        for(int i=0;i<2;i++){
            CodeDTO dto = new CodeDTO("code"+);
        }
    }

    @Test
    void getCodeTest(){

    }

    @Test
    @Order(3)
    void modifyCodeTest(){
        CodeDTO dto = codeService.findByName(sampleCodes.get(0).getCodeName());
        dto.setCodeName(codeNameToUpdate);
        String reqBody = objectMapper.writeValueAsString(dto);

        final String url = "/codes/" +dto.getId();
        mockMvc.perform(patch(url).contentType(MediaType.APPLICATION_JSON)
                .andExpect()
    }
    @Test
    @Order(4)
    void getCodesTest() throws Exception {
        List<CodeDTO> codes = codeService.getCodes();
        CodeDTO dto = codes.get(0);

        final String url = "/codes";
        mockMvc.perform(get(url)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)
                        .content()
                        .andExpect(content().json(ListJsonStr))
                        .andExpect(jsonPath)
                )

    }
    @Test
    @Order(1)
    void createCodeTest() throws Exception{
        final String url="/codes";

        for(CodeDTO dto : sampleCodes){
            String reqBody = objectMapper.writeValueAsString(dto);
            final ResultActions result = mockMvc.perform(
              post(url)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content()
                      .andDo(print());
            );
        }
    }
    @Test
    @Order(2)
    void removeCode(){
        List<CodeDTO> codes = codeService.getCodes();
        CodeDTO dto = codes.get(0);
        final String url = "/codes/" + dto.getId();
        mockMvc.perform(get(url)).get

    }
}
