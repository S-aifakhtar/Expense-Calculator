package com.expensetracker.user.service;

import java.util.List;

import com.expensetracker.user.collection.User;
import com.expensetracker.user.exception.EntityNotFoundException;

public interface UserService {

	User save(User user);

	List<User> saveAll(List<User> users);

	User update(String userId, Integer expenseAmount) throws EntityNotFoundException;

	List<User> findAll();

    void deleteById(String userId);

	void deleteAll();
}
