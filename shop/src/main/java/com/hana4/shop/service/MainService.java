package com.hana4.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hana4.shop.dao.CustDAO;
import com.hana4.shop.dto.CustDTO;

@Service
public class MainService {
				private final List<CustDTO> custs = new ArrayList<>();
				private final CustDAO dao;

				public MainService(CustDAO dao){
						this.dao = dao;
				}
				public List<CustDTO> getCusts(){
						return dao.getCusts();
				}
				public void addCust(CustDTO cust){
						dao.insert(cust);
				}
				public CustDTO find(int id) {
						return dao.getCust(id);
				}

				public void modify(CustDTO cust) {
								dao.update(cust);
				}

				public void remove(Integer id) {
						dao.delete(id);
				}
}
