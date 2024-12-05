package com.hana4.kimdohee2.repository;

import com.hana4.kimdohee2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByName(String name);

    Long addUser(User user);

    User saveUser(User user);

    User deleteUser(String id);
}
