package com.hana4.demo1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hana4.demo1.entity.Code;

public interface CodeRepository extends JpaRepository<Code, Integer> {
		List<Code> findFirstByOrderById(Pageable pageable);

		List<Code> findByCodeUsersNotEmpty();

		Optional<Code> findByCodeName(String codeName);
}
