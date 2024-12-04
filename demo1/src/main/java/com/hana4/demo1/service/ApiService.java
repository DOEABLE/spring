package com.hana4.demo1.service;

import com.hana4.demo1.dto.UserDTO;

import java.util.List;

public interface ApiService {
    public List<UserDTO> getUsers();

    public UserDTO getUser(Long id);

    public UserDTO addUser(String name, short age);

    public UserDTO modifyUser(UserDTO user) throws Exception;

    public UserDTO removeUser(Long id) throws Exception;
}
