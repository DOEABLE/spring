package com.hana4.demo1.service;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.hana4.demo1.domain.User;

class UserServiceTest {
		private UserService service = new UserService();

		@Test
		void getList() {

		}

		@Test
		void registUser() {
				User user = new User("Hong");
				Long newerId = service.regist(user);

				Optional<User> user1 = service.getUser(newerId);
				assertThat(user1.isPresent()).isTrue();
				assertThat(user1.get()).usingRecursiveComparison().isEqualTo(user);

				User userDup = new User("Hong");
				assertThatThrownBy(() -> {
						service.regist(userDup);

				}).isInstanceOf(IllegalStateException.class).hasMessageContaining("중복되었습니다.");

		}

		@Test
		void getUser() {
				Long id = 1L;
				Optional<User> user = service.getUser(id);
				assertThat(user.isPresent()).isTrue();
				assertThat(user.get()).usingRecursiveComparison().isEqualTo(new User(id, "Kim"));
		}

}
