package com.hana4.demo1.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.hana4.demo1.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import com.hana4.demo1.entity.Code;
import com.hana4.demo1.entity.CodeInfo;
import com.hana4.demo1.entity.SubCode;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// @SpringBootTest
public class CodeRepositoryTest {
    @Autowired
    CodeRepository codeRepository;

    @Autowired
    CodeInfoRepository codeInfoRepository;

    @Autowired
    SubCodeRepository subCodeRepository;

    @Test
    void codeUsersTest(){
        Code code = getCode();
        System.out.println("code = " + code);
        assertThat(code.getCodeUsers()).isNotNull();

        User user1 = new User("Hong11");
        User user2 = new User("Hong22");
        User user3 = new User("Hong33");
        List<User> users = Arrays.asList(user1, user2);
        code.setCodeUsers(users);
        code.addUser(user3);
        codeRepository.save(code);
    }

    @Test
    void addCodeWithSubCode() {
        Code code = new Code();
        code.setCodeName(getCodeName());
        codeRepository.save(code);
        assertThat(code.getId()).isGreaterThan(0);

        SubCode subCode = new SubCode();
        subCode.setValue(getCodeName());
        subCode.setCode(code);
        subCodeRepository.save(subCode);
        assertThat(subCode.getId()).isGreaterThan(0);

        System.out.println("subCode = " + subCode);
    }

    @Test
    void findCodeInfoTest() {
        CodeInfo codeInfo = getCodeInfo();
        // System.out.println("codeInfo = " + codeInfo);
        assertThat(codeInfo).isNotNull();
    }

    @Test
    void findCodeTest() {
        Code code = getCode();
        assertThat(code).isNotNull();
        assertThat(code.getCodeInfo()).isNotNull();
    }

    private Code getCode() {
        List<Code> codes = codeRepository.findFirstByOrderById(PageRequest.of(0, 1));
        System.out.println("codes = " + codes);
        return codes.stream().findFirst().orElseThrow();
    }

    private CodeInfo getCodeInfo() {
        List<CodeInfo> codeInfos = codeInfoRepository.findFirstByOrderById(PageRequest.of(0, 1));
        return codeInfos.stream().findFirst().orElseThrow();
    }

    @Test
    void addCodeTest() {
        String codeName = getCodeName();
        Code code = new Code();
        code.setCodeName(codeName);
        Code savedCode = codeRepository.save(code);
        System.out.println("savedCode = " + savedCode);
        assertThat(savedCode.getId()).isGreaterThan(0);

        CodeInfo codeInfo = new CodeInfo();
        codeInfo.setInfo("전국의 지점 모든 타입");
        codeInfo.setCode(code);
        CodeInfo savedCodeInfo = codeInfoRepository.save(codeInfo);
        System.out.println("savedCodeInfo = " + savedCodeInfo);
        assertThat(codeInfo.getId()).isGreaterThan(0);
    }

    private static String getCodeName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}