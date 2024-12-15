package com.hana4.demo1.repository;

import static com.hana4.demo1.entity.QCode.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.hana4.demo1.entity.Code;
import com.hana4.demo1.entity.CodeInfo;
import com.hana4.demo1.entity.QCode;
import com.hana4.demo1.entity.SubCode;
import com.hana4.demo1.entity.User;

// @DataJpaTest
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CodeRepositoryTest {
		@Autowired
		CodeRepository codeRepository;

		@Autowired
		CodeInfoRepository codeInfoRepository;

		@Autowired
		SubCodeRepository subCodeRepository;

		@Autowired
		UserRepository userRepository;

		@Test
		void codeListQTest() {
				QCode qCode = code;
				Iterable<Code> codes = codeRepository.findAll(code.codeName.contains("1").and(code.id.between(30, 31))
				);
				// System.out.println("codes = " + codes);
				codes.forEach(System.out::println);
				assertThat(codes).isNotEmpty();
		}

		@Test
		@Order(2)
		void removeCodeUsersTest() {
				List<Code> codes = codeRepository.findByCodeUsersNotEmpty();
				System.out.println("codes = " + codes);
				Code code = codes.get(0);
				final int oldCnt = code.getCodeUsers().size();
				code.getCodeUsers().remove(0);
				codeRepository.save(code);
				Code codeAfterRemove = codeRepository.findById(code.getId()).orElseThrow();
				assertThat(codeAfterRemove.getCodeUsers().size()).isEqualTo(oldCnt - 1);
		}

		@Test
		@Order(1)
		void codeUsersTest() {
				Code code = getCode();
				System.out.println("code = " + code);
				assertThat(code.getCodeUsers()).isNotNull();
				final int oldCnt = code.getCodeUsers().size();

				User user1 = new User("Hong11");
				userRepository.addUser(user1);
				code.addUser(user1);

				User user2 = new User("Hong22");
				userRepository.addUser(user2);
				code.addUser(user2);

				// List<User> allUsers = userRepository.findAll();
				// code.setCodeUsers(allUsers);

				codeRepository.save(code);
				System.out.println("code.getCodeUsers() = " + code.getCodeUsers());

				Code code2 = codeRepository.findById(code.getId()).orElseThrow();
				// assertThat(code.getCodeUsers().size()).isEqualTo(allUsers.size());
				assertThat(code2.getCodeUsers().size()).isEqualTo(oldCnt + 2);
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
				// CodeInfo savedCodeInfo = codeInfoRepository.save(codeInfo);
				// System.out.println("savedCodeInfo = " + savedCodeInfo);
				assertThat(codeInfo.getId()).isGreaterThanOrEqualTo(0);
		}

		private Code getCode() {
				List<Code> codes = codeRepository.findFirstByOrderById(PageRequest.of(0, 1));
				// System.out.println("codes = " + codes);
				return codes.stream().findFirst().orElseThrow();
		}

		private CodeInfo getCodeInfo() {
				List<CodeInfo> codeInfos = codeInfoRepository.findFirstByOrderById(PageRequest.of(0, 1));
				return codeInfos.stream().findFirst().orElseThrow();
		}

		private static String getCodeName() {
				return UUID.randomUUID().toString().replace("-", "");
		}

}
