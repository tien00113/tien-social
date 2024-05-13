package com.tien.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
public class HomeController {
	@GetMapping
	public String homeControllerHandler() {
		return "this is home controller";
	}
	@GetMapping("/home")
	public String homeControllerHandler2() {
		return "this is home controller 2";
	}
	@GetMapping("/tiennguyen")
	public String homeControllerHandler3() {
		return "heelo tiennguyen";
	}
//@PutMapping
//	@PostMapping
//	@DeleteMapping
}
