package com.hana4.demo1.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.hana4.demo1.domain.User;

import jakarta.persistence.EntityManager;

@Transactional

public class JpaUserRepository implements UserRepository {
		private final EntityManager em;
		final Map<Long, User> users = new HashMap<>();

		public JpaUserRepository(EntityManager em) {
				this.em = em;
		}

		@Override
		public List<User> findAll() {
				return em.createQuery("select u from User u", User.class).getResultList();
		}

		@Override
		public Long addUser(User user) {
				em.persist(user);
				return user.getId();
		}

		@Override
		public User saveUser(User user) {
				em.persist(user);
				return user;
		}

		@Override
		public User deleteUser(Long id) {
				Optional<User> user = this.findById(id);
				if (user.isPresent()) {
						em.remove(user.get());
						return user.get();
				}
				return null;
		}

		@Override
		public Optional<User> findById(Long id) {
				User user = (User)em.createQuery("select u from User u where id = :id")
						.setParameter("id", id)
						.getSingleResult();  //repository는 User임
				return Optional.ofNullable(user);
		}

		@Override
		public Optional<User> findByName(String name) {
				List<User> users = em.createQuery("select u from User u where name=:name", User.class)
						.setParameter("name", name)
						.getResultList();
				return users.stream().findAny();
		}
		public void initialize(){
			String truncSql = "truncate table DemoUser";
			em.createNativeQuery(truncSql).executeUpdate();
		}

    public void destroy() {
		System.out.println("ddddddddd");
		String[] sqls = new String[]{
				"truncate table DemoUser",
				"insert into Demo User select * from DemoUserBak",
				"drop table DemoUserBak"
		};
		for(String sql : sqls){
			em.createNativeQuery(sql).executeUpdate();
		}
    }
}
