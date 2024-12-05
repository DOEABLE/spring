package com.hana4.demo1.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.hana4.demo1.Lang;
import com.hana4.demo1.dto.LocalDTO;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hana4.demo1.entity.User;
import com.hana4.demo1.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Controller
@RequestMapping("/users")
public class UserController {
	private final String SessLocale =
			SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping()
	@ResponseBody
	public List<User> getUsers() {
		logger.trace("tttttttrace!!!");
		logger.debug("dddddddddebug!!!");
		logger.info("iiiiiiiiiiinfo!!!");
		logger.warn("wwwwwwwwwwwarn!!!");
		logger.error(("eeeeeeeeeeerrror!!"));
		return service.getList();
	}

	@GetMapping("/list")
	public String userList(Model model,HttpSession session) {
		model.addAttribute("users", service.getList());
		model.addAttribute("Langs", Lang.values());
		model.addAttribute("currLang", session.getAttribute(SessLocale));
		return "user/list";
	}

	@PostMapping("/changelang")
	public String changeLang(@ModelAttribute LocalDTO dto, HttpSession session){
		session.setAttribute(SessLocale, dto.getLocale());
		return "redirect:/users/list";
	}

	@PostMapping()
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
	public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
		Optional<User> user = service.getUser(id);
		// if (user.isPresent()) {
		// return ResponseEntity.ok(user.get());
		return ResponseEntity.of(user);
		// return ResponseEntity.ofNullable(user);
		// } else {
		// return ResponseEntity.status(404).body("Not Found");
		// }
	}
	/*
	public User getUser(@PathVariable("id") Long id, HttpServletResponse res) throws IOException {
		Optional<User> user = service.getUser(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			res.sendError(404, "User not found!");
			return null;
		}
	}
	 */

	private User checkExists(Long id, HttpServletResponse response) throws IOException {
		Optional<User> user = service.getUser(id);
		if (user.isEmpty()) {
			response.sendError(404, "User not found!");
			return null;
		}

		return user.get();
	}

	// @PatchMapping("/users/{id}")
	@RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
	@ResponseBody
	public User updateUser(@PathVariable("id") Long id, @RequestBody User user, HttpServletResponse res) throws
			IOException {
		System.out.println("id = " + id);
		user.setId(id);
		System.out.println("user = " + user);
		User attachedUser = checkExists(user.getId(), res);
		assert attachedUser != null;
		// if (attachedUser == null) {
		// 	res.sendError(404);
		// 	return null;
		// }
		attachedUser.setName(user.getName());
		return service.updateUser(attachedUser);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public User deleteUser(@PathVariable("id") Long id, HttpServletResponse res) throws IOException {
		checkExists(id, res);
		return service.deleteUser(id);
	}
}

