package com.hana4.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.hana4.shop.dto.CustDTO;
@Repository
@Mapper
public interface CustDAO {//.xml파일과 매핑되어있는 파일.
		Integer addCust(String name, String tel, String email);
		List<CustDTO> getCusts();//custs라고 명사로 명명할 수도 있음.
		CustDTO getCust(int id);

		void update(CustDTO cust);

		void insert(CustDTO cust);

		void delete(Integer id);
}
