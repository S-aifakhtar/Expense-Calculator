package com.expensetracker.user.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.expensetracker.user.collection.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {}
