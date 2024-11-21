package com.hana4.demo1.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.hana4.demo1.domain.User;

public class VolatileUserRepository implements UserRepository {

		final Map<Long, User> users = new HashMap<>();

		@Override
		public List<User> findAll() {
				return new ArrayList(users.values());
		}

		@Override
		public Long addUser(User user) {
				Set<Long> userIds = users.keySet();
				Long maxId = userIds.stream().max(Long::compare).orElse(0L);

				user.setId(maxId + 1);
				users.put(user.getId(), user);
				return user.getId();
		}

		@Override
		public User saveUser(User user) {
				users.put(user.getId(), user);
				return null;
		}

		@Override
		public User deleteUser(Long id) {

				return users.remove(id);
		}

		@Override
		public Optional<User> findById(Long id) {
				return Optional.ofNullable(users.get(id));
		}

		@Override
		public Optional<User> findByName(String name) {
				return users.values().stream().filter(user -> user.getName().equals(name)).findAny();
		}
}
