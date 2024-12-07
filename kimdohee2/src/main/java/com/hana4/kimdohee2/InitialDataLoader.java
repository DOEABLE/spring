package com.hana4.kimdohee2;

import com.hana4.kimdohee2.entity.User;
import com.hana4.kimdohee2.repository.UserRepository;
import com.hana4.kimdohee2.entity.User;
import com.hana4.kimdohee2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component  //spring bean으로 등록됨.
public class InitialDataLoader implements ApplicationRunner {
    private final UserRepository repository;

    public InitialDataLoader(UserRepository repository){
        this.repository=repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> names = Arrays.asList("Kim1", "Kim2", "Kim3", "Kim4", "Kim5");

        for (String name : names) {
            User user = new User();
            user.setName(name);
            user.setEmail(name.toLowerCase() + "@gmail.com");

            try {
                repository.saveUser(user);
                System.out.println("사용자 등록 완료: " + name);
            } catch (DataIntegrityViolationException e) {
                System.out.println("사용자 이름 중복: " + name + ". 건너뜁니다.");
            }
        }
//        Optional<User> user10 = repository.findByName("AA10");
//        if(user10.isPresent()){
//            return;
//        }
//        repository.initialize();
//
//        short s =10;
//        repository.addUser(new User("AA"+s,s++));
//        repository.addUser(new User("AA"+s,s++));
//        repository.addUser(new User("AA"+s,s));
    }
}
