package com.hana4.demo1.repository;

import com.hana4.demo1.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,String> {
    @Query("select p from Post p where p.writer = :writer and p.createdate <= :dateTime")
    List<Post> findByAnything(@Param("writer") String writer, @Param("dateTime")LocalDateTime dateTime);
}
