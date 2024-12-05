package com.hana4.demo1.service;

import com.hana4.demo1.dao.ApiDAO;
import com.hana4.demo1.dto.UserDTO;
import com.hana4.demo1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService{
    private final ApiDAO dao;

    @Autowired
    public ApiServiceImpl(ApiDAO dao){
        this.dao = dao;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = dao.selectAll(); //selectAll 매서드를 호출해 db에서 모든 User엔티티 조회
        return users.stream()
                .map(u -> UserDTO.builder().id(u.getId()).name(u.getName()).age(u.getAge()).build()).toList();
    }

    @Override
    public UserDTO getUser(Long id) {
        return dao.select(id).map(User::toDTO).orElse(null);
    }

    @Override
    public UserDTO addUser(String name, short age) {
        return dao.insert(name,age).toDTO();
    }

    @Override
    public UserDTO modifyUser(UserDTO user) throws Exception {
        return dao.update(user).toDTO();
    }

    @Override
    public UserDTO removeUser(Long id) throws Exception {
        return dao.delete(id).toDTO();
    }
}
