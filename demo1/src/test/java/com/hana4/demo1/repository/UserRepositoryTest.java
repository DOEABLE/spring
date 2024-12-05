package com.hana4.demo1.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hana4.demo1.entity.User;

public class UserRepositoryTest {

	final UserRepository repository = new VolatileUserRepository();

	@BeforeEach
	public void beforeEach() {
		repository.initialize();
	}

	@Test
	public void addUser() {
		int preCount = repository.findAll().size();
		User user = new User(0L, "Hong");
		Long newerId = repository.addUser(user);
		assertEquals(2, newerId);

		Optional<User> newer = repository.findById(newerId);
		assertThat(newer.isPresent()).isTrue();
		// newer.ifPresent(a -> assertThat(a).isEqualTo(user));
		assertThat(newer.get()).isEqualTo(user);
		assertThat(newer.get()).usingRecursiveComparison().isEqualTo(new User(newerId, "Hong"));

		final List<User> allUsers = repository.findAll();
		assertThat(allUsers.size()).isGreaterThan(1);

		assertThat(allUsers.size()).isEqualTo(preCount + 1);

	}

	@Test
	public void saveUser() {
		String name = "Kim22";
		String name1 = "Kim11";

		Optional<User> user = repository.findById(1L);
		user.ifPresent(a -> {
			a.setName(name);
			User tmpUser = repository.saveUser(a);
			assertThat(tmpUser.getName()).isEqualTo(name);
		});

		User user1 = new User(1L, name1);
		User savedUser = repository.saveUser(user1);
		assertThat(savedUser).isEqualTo(user1);
	}

	@Test
	public void deleteUser() {
		int preCount = repository.findAll().size();

		Long delId = 1L;
		Optional<User> toDeleteUser = repository.findById(delId);
		User deletedUser = repository.deleteUser(delId);

		toDeleteUser.ifPresent(a -> {
			assertThat(a.getId()).isEqualTo(delId);
			assertThat(a).isEqualTo(deletedUser);
		});

		int afterCount = repository.findAll().size();
		assertThat(afterCount).isEqualTo(preCount - 1);
	}
}