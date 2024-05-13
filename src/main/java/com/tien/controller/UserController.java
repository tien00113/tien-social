package com.tien.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tien.exceptions.UserException;
import com.tien.models.Post;
import com.tien.models.User;
import com.tien.repository.UserRepository;
import com.tien.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@GetMapping("/api/users")
	public List<User> getUsers() {

		List<User> users = userRepository.findAll();

		return users;
	}

	@GetMapping("/api/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) throws UserException {

		User user = userService.findUserById(id);
		return user;
	}

	@PostMapping("/users")
	public User createUser(@RequestBody User user) {

		User savedUser = userService.registerUser(user);

		return savedUser;
	}
	
	@PutMapping("/api/users")
	public User updateUser(@RequestHeader("Authorization")String jwt, @RequestBody User user) throws UserException {

		User reqUser = userService.findUserByJwt(jwt);
		
		User updatedUser = userService.updateUser(user, reqUser.getId());
		
		return updatedUser;
	}
	
	@PatchMapping("/api/users")
	public User patchUpdateUserImage(@RequestHeader("Authorization")String jwt, @RequestBody User user) throws UserException{
		User reqUser = userService.findUserByJwt(jwt);
		
		User updatedUser = userService.patchUpdateUserImage(user, reqUser.getId());
		
		return updatedUser;
	}
	
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization")String jwt,@PathVariable Integer userId2) throws UserException {
		
		User reqUser = userService.findUserByJwt(jwt);
		User user = userService.followUser(reqUser.getId() ,userId2);
		
		return user;
	}
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		List<User> users = userService.searchUser(query);
		
		return users;
	}
	
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization")String jwt) {
		
		User user = userService.findUserByJwt(jwt);
		
		user.setPassword(null);
		return user;
	}

	@GetMapping("/api/users/{userId}/savedPosts")
	public List<Post> getSavedPosts(@PathVariable Integer userId) {
    User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("user not found with id"+userId));
	
    return user.getSavedPost();
} 
	
}
