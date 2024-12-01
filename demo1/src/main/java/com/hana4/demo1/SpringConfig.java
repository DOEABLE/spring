package com.hana4.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hana4.demo1.repository.JpaUserRepository;
import com.hana4.demo1.repository.UserRepository;
import com.hana4.demo1.service.UserService;

import jakarta.persistence.EntityManager;

@Configuration //springBoot이 annotation을 찾음.
public class SpringConfig {
		private final EntityManager em;

		public SpringConfig(EntityManager em) {
				this.em = em;
		}

		@Bean
		public UserService userService() {
				return new UserService(userRepository());
		}

		@Bean
		public UserRepository userRepository() {
				return new JpaUserRepository(em);
		}
}
