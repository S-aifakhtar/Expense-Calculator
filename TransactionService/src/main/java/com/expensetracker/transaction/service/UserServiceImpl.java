package com.expensetracker.transaction.service;

import com.expensetracker.transaction.collection.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link UserService} interface for managing user-related operations.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WebClient webClient;

    /**
     * Fetch all users from the specified URL.
     *
     * @param usersUrl The URL for fetching users.
     * @return List of all users.
     */
    public List<User> fetchAllUsers(String usersUrl) {
        return webClient.get()
                .uri(usersUrl + "/show")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<User>>() {})
                .block();
    }

    /**
     * Fetch the main user based on the given user name.
     *
     * @param userName The name of the main user.
     * @param allUsers List of all users.
     * @return The main user.
     * @throws RuntimeException if the main user is not found.
     */
    public User fetchMainUser(String userName, List<User> allUsers) {
        return allUsers.stream()
                .filter(u -> u.getName().equals(userName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Main user not found for name: " + userName));
    }

    /**
     * Fetch other users excluding the main user.
     *
     * @param userName The name of the main user.
     * @param allUsers List of all users.
     * @return List of other users.
     */
    public List<User> fetchOtherUsers(String userName, List<User> allUsers) {
        return allUsers.stream()
                .filter(u -> !u.getName().equals(userName))
                .collect(Collectors.toList());
    }
}
