package com.hana4.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hello {
		@GetMapping("/hello")
		public String hello(Model model) {
				model.addAttribute("name", " dohee");
				return "hello";
		}

		@GetMapping("/hi")
		@ResponseBody
		public Dog hi(@RequestParam("name") String name) {
				Dog maxx = new Dog(name);
				System.out.println("maxx=" + maxx);
				return maxx;
		}

		static class Dog {
				private String name;

				public String getName() {
						return name;
				}

				public void setName(String name) {
						this.name = name;
				}

				public Dog(String name) {
						this.name = name;
				}
		}
}
