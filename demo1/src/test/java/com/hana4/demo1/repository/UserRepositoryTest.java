package com.hana4.demo1.repository;

import org.junit.jupiter.api.Test;

import com.hana4.demo1.domain.User;

public class UserRepositoryTest {

		final UserRepository repository = new VolatileUserRepository();

		@Test
		public void addUser() {
				User user = new User(0L, "Hong");
				long newerId = repository.addUser(user);
				assertEqauls(1, newerId);
		}

		private void assertEqauls(int i, long newerId) {

		}

		@Test
		public void saveUSer() {

		}

}
