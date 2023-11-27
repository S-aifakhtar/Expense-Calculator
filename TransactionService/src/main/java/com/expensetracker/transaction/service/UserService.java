package com.expensetracker.transaction.service;

import com.expensetracker.transaction.collection.User;

import java.util.List;

public interface UserService {

    /**
     * Fetch all users from the specified URL.
     *
     * @param usersUrl The URL for fetching users.
     * @return List of all users.
     */
    List<User> fetchAllUsers(String usersUrl);

    /**
     * Fetch the main user based on the given user name.
     *
     * @param userName The name of the main user.
     * @param allUsers List of all users.
     * @return The main user.
     */
    User fetchMainUser(String userName, List<User> allUsers);

    /**
     * Fetch other users excluding the main user.
     *
     * @param userName The name of the main user.
     * @param allUsers List of all users.
     * @return List of other users.
     */
    List<User> fetchOtherUsers(String userName, List<User> allUsers);
}
