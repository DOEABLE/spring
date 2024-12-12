package com.hana4.kimdohee2.repository;

import com.hana4.kimdohee2.dto.PostDTO;
import com.hana4.kimdohee2.entity.Post;
import com.hana4.kimdohee2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
