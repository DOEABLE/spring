//package com.hana4.kimdohee2;
//
//import com.hana4.kimdohee2.entity.Post;
//import com.hana4.kimdohee2.repository.UserRepository;
//import com.hana4.kimdohee2.service.PostService;
//import com.hana4.kimdohee2.service.UserService;
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import jakarta.persistence.EntityManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SpringConfig {
//    private final EntityManager em;
//    private final UserRepository userRepository;
//
//    public SpringConfig(EntityManager em, UserRepository userRepository) {
//        this.em = em;
//        this.userRepository = userRepository;
//    }
//
////    @Bean
////    public UserService userService() {
////        return new UserService(userRepository);
////    }
//
//
//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI()
//                .components(new Components())
//                .info(info());
//    }
//
//    private Info info() {
//        return new Info()
//                .version("0.1.0")
//                .title("Demo Api Spec.")
//                .description("...");
//    }
//}
