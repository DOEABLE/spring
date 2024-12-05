package com.hana4.kimdohee2.repository;

import com.hana4.kimdohee2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository  extends JpaRepository<Post,Long> {
}
