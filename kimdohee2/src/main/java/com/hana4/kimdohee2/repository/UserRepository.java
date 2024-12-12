package com.hana4.kimdohee2.repository;

import com.hana4.kimdohee2.dto.PostDTO;
import com.hana4.kimdohee2.entity.Post;
import com.hana4.kimdohee2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
////    List<User> findAll();
////    Optional<User> findById(String id);
//    Optional<User> findByName(String name);
////
    String addUser(User user);
////
//    User saveUser(User user);
////
//    User deleteUser(String id);
////
////    void initialize();  //데이터 초기화
////
////    void destroy();     //데이터 복구
////
//    boolean existsById(String id);

}
