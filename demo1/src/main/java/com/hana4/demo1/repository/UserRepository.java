package com.hana4.demo1.repository;

import java.util.List;
import java.util.Optional;

import com.hana4.demo1.entity.User;

public interface UserRepository {
		List<User> findAll();

		Long addUser(User user);

		User saveUser(User user);

		User deleteUser(Long id);

		Optional<User> findById(Long id);

		Optional<User> findByName(String name);

		void initialize();
		void destroy();
}
