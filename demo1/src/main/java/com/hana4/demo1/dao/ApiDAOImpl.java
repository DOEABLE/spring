package com.hana4.demo1.dao;

import com.hana4.demo1.entity.User;
import com.hana4.demo1.dto.UserDTO;
import com.hana4.demo1.repository.ApiRepository;

import java.util.List;
import java.util.Optional;

public class ApiDAOImpl implements ApiDAO{
    private final ApiRepository repository;

    public ApiDAOImpl(ApiRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> selectAll() {
        return repository.findAll();
    }

    @Override
    public Optional<com.hana4.demo1.entity.User> select(Long id) {
        return repository.findById(id);
    }

    @Override
    public User insert(String name, short age) {
        User user = new User(name, age);
        return repository.save(user);
    }

    @Override
    public User update(UserDTO user) {
        Optional<User> pUser = repository.findById(user.getId());
        if (pUser.isPresent()) {
            User managedUser = pUser.get();
            managedUser.setName(user.getName());
            if (user.getAge() > 0) {
                managedUser.setAge(user.getAge());
            }
            return repository.save(managedUser);
        } else {
            throw new IllegalStateException("User not Found!");
        }
    }

    @Override
    public User delete(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            repository.delete(user.get());
            return user.get();
        } else {
            throw new IllegalStateException("User not Found!");
        }
    }

}
