package com.hana4.demo1.repository;

import com.hana4.demo1.entity.CodeInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeInfoRepository extends JpaRepository<CodeInfo, Integer> {
    List<CodeInfo> findFirstByOrderById(Pageable pageable);
}
