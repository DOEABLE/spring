package com.hana4.demo1.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hana4.demo1.domain.User;
import com.hana4.demo1.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/users")
public class UserController {

		private final UserService service;

		public UserController(UserService service) {
				this.service = service;
		}

		@GetMapping("")
		@ResponseBody
		public List<User> getUsers() {
				return service.getList();
		}

		@GetMapping("/list")
		public String userList(Model model) {
				model.addAttribute("users", service.getList());
				return "user/list";
		}

		@PostMapping("")
		@ResponseBody
		public User regist(@RequestBody User user) throws BadRequestException {
				Long newerId = service.regist(user);
				Optional<User> newer = service.getUser(newerId);
				if (newer.isPresent()) {
						return newer.get();
				} else {
						throw new BadRequestException("InsertError");
				}
		}

		@PostMapping("/add")
		public String registAdd(User user) {
				System.out.println("user = " + user);
				service.regist(user);
				return "redirect:/users/list";
		}

		@GetMapping("/{id}")
		@ResponseBody
		public User getUser(@PathVariable("id") Long id, HttpServletResponse res) throws IOException {
				Optional<User> user = service.getUser(id);
				if (user.isPresent()) {
						return user.get();
				} else {
						res.sendError(404, "User not found!");
						return null;
				}
		}

		private void checkExists(Long id, HttpServletResponse response) throws IOException {
				if (service.getUser(id).isEmpty()) {
						response.sendError(404, "User not found!");
				}
		}

		// @PatchMapping("/users/{id}")
		@RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
		@ResponseBody
		public User updateUser(@PathVariable("id") Long id, @RequestBody User user, HttpServletResponse res) throws
				IOException {
				System.out.println("id = " + id);
				user.setId(id);
				System.out.println("user = " + user);
				checkExists(user.getId(), res);

				return service.updateUser(user);
		}

		@DeleteMapping("/{id}")
		@ResponseBody
		public User deleteUser(@PathVariable("id") Long id, HttpServletResponse res) throws IOException {
				checkExists(id, res);
				return service.deleteUser(id);
		}

}
