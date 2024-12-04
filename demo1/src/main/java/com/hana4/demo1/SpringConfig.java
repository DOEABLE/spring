package com.hana4.demo1;

import com.hana4.demo1.dao.ApiDAO;
import com.hana4.demo1.dao.ApiDAOImpl;
import com.hana4.demo1.repository.ApiRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hana4.demo1.repository.JpaUserRepository;
import com.hana4.demo1.repository.UserRepository;
import com.hana4.demo1.service.UserService;

import jakarta.persistence.EntityManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration //springBoot이 annotation을 찾음.
public class SpringConfig {
	private final EntityManager em;
	private final ApiRepository apiRepository;

	public SpringConfig(EntityManager em, ApiRepository apiRepository) {
		this.em = em;
		this.apiRepository = apiRepository;
	}

	@Bean
	public UserService userService() {
		return new UserService(userRepository());
	}

	@Bean
	public UserRepository userRepository() {
		// return new VolatileUserRepository();
		return new JpaUserRepository(em);
	}

	@Bean
	public ApiDAO apiDAO() {
		return new ApiDAOImpl(apiRepository);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver sessionResolver = new SessionLocaleResolver();
		sessionResolver.setDefaultLocale(Locale.KOREAN);
		return sessionResolver;
	}

}
