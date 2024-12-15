package com.hana4.demo1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// @ControllerAdvice  //@RestController 안써도 됨.
@RestController
public class CustomErrorController {
		@ExceptionHandler(Exception.class)
		public String exceptionHandler(Exception e, Model model) {
				model.addAttribute("msg", e.getMessage());
				return "error/error";
		}

		@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
		@ExceptionHandler
		// @ResponseBody
		public ResponseEntity<?> handleError(RuntimeException e) {
				// ErrorResult er = new ErrorResult(e);
				return ResponseEntity.status(500).body(e.getMessage());
		}
}
