package com.hana4.kimdohee2.service;

import com.hana4.kimdohee2.dto.UserDTO;
import com.hana4.kimdohee2.entity.User;
import com.hana4.kimdohee2.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public List<User> getList() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String id) {
        return userRepository.findById(id);
    }

    public UserDTO updateUser(String id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    //특정 사용자 조회

    public UserDTO getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        return convertToDTO(user);
    }
    // 엔티티를 DTO로 변환하는 헬퍼 메서드

    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    // 사용자 추가
    public UserDTO addUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public void deleteUser(String id) {
        //Long userId = Long.parseLong(id);
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    public User updateUser(User attachedUser) {
        return userRepository.save(attachedUser);
    }
}
//    public User deleteUser(String id) {
//        return userRepository.deleteUser(id);
//    }
//}
