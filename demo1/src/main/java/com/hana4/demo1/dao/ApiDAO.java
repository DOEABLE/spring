package com.hana4.demo1.dao;

import com.hana4.demo1.entity.User;
import com.hana4.demo1.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface ApiDAO {
    public List<User> selectAll();
    public Optional<User> select(Long id);
    public User insert(String name, short age);
    public User update(UserDTO user);
    public User delete(Long id);

}
