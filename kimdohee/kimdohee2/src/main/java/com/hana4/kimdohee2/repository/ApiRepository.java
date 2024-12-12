package com.hana4.kimdohee2.repository;

import com.hana4.kimdohee2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<User,String> {
}
