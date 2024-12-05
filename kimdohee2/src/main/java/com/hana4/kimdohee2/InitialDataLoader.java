package com.hana4.kimdohee2;

import com.hana4.demo1.entity.User;
import com.hana4.demo1.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component  //spring bean으로 등록됨.
public class InitialDataLoader implements ApplicationRunner {
    private final UserRepository repository;

    public InitialDataLoader(UserRepository repository){
        this.repository=repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<User> user10 = repository.findByName("AA10");
        if(user10.isPresent()){
            return;
        }
        repository.initialize();

        short s =10;
        repository.addUser(new User("AA"+s,s++));
        repository.addUser(new User("AA"+s,s++));
        repository.addUser(new User("AA"+s,s));
    }
}
