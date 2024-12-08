package com.hana4.kimdohee1;

import com.hana4.kimdohee1.dao.BookRepository;
import com.hana4.kimdohee1.dto.Book;
import com.hana4.kimdohee1.service.BookService;
import com.hana4.kimdohee1.service.BookServiceImpl;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration // 꼭! springBoot이 annotation을 찾음.
@MapperScan(basePackages = "com.hana4.kimdohee1.dao")
public class SpringConfig {

    private BookRepository repository;

    public SpringConfig(BookRepository repository){
        this.repository=repository;
    }
        @Bean
        public LocaleResolver localeResolver() {
            SessionLocaleResolver sessionResolver = new SessionLocaleResolver();
            sessionResolver.setDefaultLocale(Locale.KOREAN);
            return sessionResolver;
        }


        @Bean
        public OpenAPI openAPI() {
            return new OpenAPI()
                    .components(new Components())
                    .info(info());
        }

        private Info info() {
            return new Info()
                    .version("0.1.0")
                    .title("Demo Api Spec.")
                    .description("...");
        }
    }
