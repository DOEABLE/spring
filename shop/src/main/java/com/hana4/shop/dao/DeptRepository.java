package com.hana4.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.hana4.shop.dto.Dept;
import com.hana4.shop.dto.Emp;

@Repository
@Mapper
public interface DeptRepository {
		List<Dept> getList(int id);

		Dept find(Integer id);

		List<Emp> getEmps();

		void insert(Dept dept);

		void update(Dept dept);

		List<Dept> findByPid(int id);

		void delete(int id);
}
