package com.hana4.kimdohee2.controller;

import com.hana4.kimdohee2.dto.UserDTO;
import com.hana4.kimdohee2.entity.User;
import com.hana4.kimdohee2.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userservice){
        this.userService = userservice;
    }
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    @ResponseBody
    public User updateUser(@PathVariable("id") String id, @RequestBody User user, HttpServletResponse response) throws IOException {
        System.out.println("id = " + id);
        user.setId(id);
        System.out.println("user = " + user);
        User attachedUser = checkExists(user.getId(), response);
        assert attachedUser != null;
        attachedUser.setName(user.getName());
        return userService.updateUser(attachedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
    private User checkExists(String id, HttpServletResponse response) throws IOException {
        Optional<User> user = userService.getUser(id);
        if (user.isEmpty()) {
            response.sendError(404, "User not found!");
            return null;
        }

        return user.get();
    }
}
