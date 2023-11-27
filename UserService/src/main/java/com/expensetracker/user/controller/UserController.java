package com.expensetracker.user.controller;

import java.util.List;

import com.expensetracker.user.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.expensetracker.user.collection.User;
import com.expensetracker.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/add-user")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		User savedUser = this.userService.save(user);
		return ResponseEntity.ok(savedUser);
	}

	@PostMapping("/add-users")
	public ResponseEntity<?> addUsers(@RequestBody List<User> users) {
		List<User> savedUsers = this.userService.saveAll(users);
		return ResponseEntity.ok(savedUsers);
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody Integer expense) {
		try {
			User updatedUser = this.userService.update(userId, expense);
			return ResponseEntity.ok(updatedUser);
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body("Invalid argument provided");
		}
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId) {
		try {
			userService.deleteById(userId);
			return ResponseEntity.ok("User deleted successfully");
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + userId + " not found");
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body("Invalid argument provided");
		}
	}

	@DeleteMapping("/delete-all")
	public ResponseEntity<?> deleteAllUsers() {
		try {
			userService.deleteAll();
			return ResponseEntity.ok("All users deleted successfully");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete all users");
		}
	}

	@GetMapping("/show")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = this.userService.findAll();
		return ResponseEntity.ok(users);
	}
}
