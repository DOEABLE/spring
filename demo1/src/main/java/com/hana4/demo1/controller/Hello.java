package com.hana4.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hello {
		@RequestMapping(value = "/hello", method = {RequestMethod.GET, RequestMethod.POST})
		public String hello(Model model) {
				model.addAttribute("name", "Sico");

				return "hello";
		}

		@GetMapping(value = "/hi", params = "name=xxx")
		@ResponseBody
		public Dog hi(@RequestParam("name") String name) {
				Dog maxx = new Dog(name);
				System.out.println("Dog.name = " + maxx);
				return maxx;
		}

		public static class Dog {
				private String name;

				public Dog(String name) {
						this.name = name;
				}

				public String getName() {
						return name;
				}

				public void setName(String name) {
						this.name = name;
				}
		}
}
