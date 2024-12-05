package com.hana4.kimdohee2.service;

import com.hana4.kimdohee2.entity.User;
import com.hana4.kimdohee2.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    private UserRepository repository;
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public List<User> getList() {
        return repository.findAll();
    }

    public Long regist(User user) {
        repository.findByName(user.getName()).ifPresent(u -> {
            throw new IllegalStateException("Duplicate name!");
        });
        return repository.addUser(user);
    }

    public Optional<User> getUser(String id) {
        return repository.findById(id);
    }

    public User updateUser(User user) {
        return repository.saveUser(user);
    }

    public User deleteUser(String id) {
        return repository.deleteUser(id);
    }

}
