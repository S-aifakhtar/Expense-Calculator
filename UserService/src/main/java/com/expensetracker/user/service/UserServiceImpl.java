package com.expensetracker.user.service;

import java.util.List;

import com.expensetracker.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expensetracker.user.collection.User;
import com.expensetracker.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		// Null check for userRepository
		if (userRepository != null) {
			return userRepository.save(user);
		}
		// Return null if userRepository is null
		return null;
	}

	@Override
	public List<User> saveAll(List<User> users) {
		// Null check for userRepository
		if (userRepository != null) {
			return userRepository.saveAll(users);
		}
		// Return null if userRepository is null
		return null;
	}

	@Override
	public User update(String userId, Integer expense) throws UserNotFoundException {
		// Throws UserNotFoundException if user not found
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

		existingUser.setExpense(expense);
		return userRepository.save(existingUser);
	}


	@Override
	public void deleteById(String userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
}
