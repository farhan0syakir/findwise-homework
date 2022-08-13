package com.findwise.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	// todo create upload
	// todo create cache

	@RequestMapping("/")
	public @ResponseBody
	String greeting() {
		return "Hello, World";
	}

}