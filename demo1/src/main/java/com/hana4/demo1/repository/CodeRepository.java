package com.hana4.demo1.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hana4.demo1.entity.Code;

public interface CodeRepository extends JpaRepository<Code, Integer> {
    List<Code> findFirstByOrder
    List<Code> findFirstByOrderById(Pageable pageable);
}
