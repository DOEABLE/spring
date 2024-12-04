package com.hana4.demo1.repository;

import com.hana4.demo1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<User,Long> {


}
