package com.hana4.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hana4.demo1.repository.UserRepository;
import com.hana4.demo1.repository.VolatileUserRepository;
import com.hana4.demo1.service.UserService;

@Configuration
public class SpringConfig {
		@Bean
		public UserService userService() {
				return new UserService(userRepository());
		}

		@Bean
		public UserRepository userRepository() {
				return new VolatileUserRepository();
		}
}
