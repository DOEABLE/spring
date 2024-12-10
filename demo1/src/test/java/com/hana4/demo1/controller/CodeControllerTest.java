package com.hana4.demo1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hana4.demo1.dto.CodeDTO;
import com.hana4.demo1.dto.SubCodeDTO;
import com.hana4.demo1.repository.CodeRepository;
import com.hana4.demo1.service.CodeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CodeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private final List<CodeDTO> sampleCodes = new ArrayList<>();
    @Autowired
    private CodeService codeService;

    @BeforeAll
    void BeforeAll(@Autowired CodeRepository codeRepository) {
        System.out.println("CodeControllerTest.BeforeAll - -");

        // cleaqn
        codeRepository.deleteAll();

        for (int i = 0; i < 2; i++) {
            CodeDTO dto = new CodeDTO("Code" + i);
            dto.setSubcodes(List.of(
                    new SubCodeDTO("ValueX" + i),
                    new SubCodeDTO("ValueY" + i)
            ));
            sampleCodes.add(dto);
        }
    }

    @Test
    @Order(9)
    void removeSubCodeTest() throws Exception {
        CodeDTO dto = getCodeDTO(true);
        final List<SubCodeDTO> subCodes = dto.getSubcodes();
        final SubCodeDTO subCode = subCodes.get(0);

        final String url = "/codes/" + dto.getId() + "/subcodes/" + subCode.getId();
        // final String url = "/codes/subcodes/" + subCode.getId();

        mockMvc.perform(delete(url))
                .andExpect(status().is(200))
                .andExpect(content().string("1"))
                .andDo(print());

        final int afterRemoveCnt = getCodeDTO(true).getSubcodes().size();
        // System.out.println("--------->> afterRemoveCnt = " + afterRemoveCnt);
        assertThat(afterRemoveCnt).isLessThan(subCodes.size());
    }

    @Test
    @Order(8)
    void modifySubCodeTest() throws Exception {
        CodeDTO dto = getCodeDTO(true);
        final SubCodeDTO subCode = dto.getSubcodes().get(0);
        final String newValue = subCode.getValue() + "New!!";
        final int newValueLen = newValue.length();
        if (newValueLen > 32) {
            subCode.setValue(newValue.substring(newValueLen - 32));
        } else {
            subCode.setValue(newValue);
        }
        final String bodyStr = objectMapper.writeValueAsString(subCode);

        // final String url = "/codes/" + dto.getId() + "/subcodes/" + subCode.getId();
        final String url = "/codes/subcodes/" + subCode.getId();

        mockMvc.perform(patch(url).contentType(MediaType.APPLICATION_JSON).content(bodyStr))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", subCode.getId()).exists())
                .andExpect(jsonPath("$.value").value(subCode.getValue()))
                .andExpect(jsonPath("$.value", subCode.getValue()).exists())
                .andExpect(jsonPath("$.createAt", subCode.getCreateAt()).exists())
                // .andExpect(jsonPath("$.updateAt").value(not(subCode.getUpdateAt())))
                .andDo(print());

    }

    @Test
    @Order(7)
    void getSubCodesTest() throws Exception {
        CodeDTO dto = getCodeDTO();
        final int subCodeCnt = dto.getSubcodes().size();

        final String url = "/codes/" + dto.getId() + "/subcodes";

        mockMvc.perform(get(url)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(subCodeCnt))
                .andDo(print());
    }

    @Test
    @Order(6)
    void addSubCodeTest() throws Exception {
        CodeDTO dto = getCodeDTO();
        final String url = "/codes/" + dto.getId() + "/subcodes";

        String value = getUuid();
        SubCodeDTO subCodeDTO = new SubCodeDTO(value);
        String bodyStr = objectMapper.writeValueAsString(subCodeDTO);

        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(bodyStr))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.value").value(value))
                .andDo(print());
    }

    @Test
    @Order(5)
    void removeCodeTest() throws Exception {
        CodeDTO dto = getCodeDTO();
        final String url = "/codes/" + dto.getId();

        mockMvc.perform(delete(url))
                .andExpect(status().isOk())
                .andExpect(content().string("1"))
                .andDo(print());
    }

    @Test
    @Order(4)
    void getCodeTest() throws Exception {
        CodeDTO dto = getCodeDTO();
        final String url = "/codes/" + dto.getId();
        mockMvc.perform(get(url)).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)))
                .andDo(print());

        // assertThat(codes).containsExactlyElementsOf(codes);

    }

    @Test
    @Order(3)
    void modifyCodeTest() throws Exception {
        String codeNameToUpdate = "ModifiedCodeName";
        CodeDTO dto = codeService.findByName(sampleCodes.get(0).getCodeName());
        dto.setCodeName(codeNameToUpdate);
        String reqBody = objectMapper.writeValueAsString(dto);

        final String url = "/codes/" + dto.getId();
        mockMvc.perform(patch(url).contentType(MediaType.APPLICATION_JSON).content(reqBody)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.codeName").value(codeNameToUpdate))
                .andExpect(jsonPath("$.createAt").value(dto.getCreateAt().toString()))
                .andDo(print());
    }

    @Test
    @Order(2)
    void getCodesTest() throws Exception {
        final String url = "/codes";
        mockMvc.perform(get(url)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // .andExpect(content().json(listJsonStr))
                .andExpect(jsonPath("$[0].codeName").value(sampleCodes.get(0).getCodeName()))
                .andExpect(jsonPath("$[1].codeName").value(sampleCodes.get(1).getCodeName()))
                .andExpect(jsonPath("$[0].createAt").isNotEmpty())
                .andExpect(jsonPath("$[1].updateAt").isNotEmpty())
                .andDo(print());
        System.out.println("sampleCodes = " + sampleCodes);
    }

    @Test
    @Order(1)
    void createCodeTest() throws Exception {
        final String url = "/codes";

        for (CodeDTO dto : sampleCodes) {
            String reqBody = objectMapper.writeValueAsString(dto);
            System.out.println("reqBody = " + reqBody);

            final ResultActions result = mockMvc.perform(
                    post(url)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(reqBody));

            result.andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").isNotEmpty())
                    .andExpect(jsonPath("$.codeName").value(dto.getCodeName()))
                    .andDo(print());
        }
    }

    private CodeDTO getCodeDTO() {
        return getCodeDTO(false);
    }

    private CodeDTO getCodeDTO(boolean hasSubCodes) {
        List<CodeDTO> codes = hasSubCodes ?
                codeService.getCodesHaveSubCodes() : codeService.getCodes();
        return codes.get(0);
    }

    private String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}