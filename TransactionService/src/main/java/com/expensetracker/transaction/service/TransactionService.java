package com.expensetracker.transaction.service;

import com.expensetracker.transaction.collection.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class TransactionService {
    Logger logger = LoggerFactory.getLogger(TransactionService.class);
    @Value("${service.urls.users}")
    private String usersUrl;

    private static final int EXPENSE_AFTER_SETTLEMENT = 0;
    @Autowired
    private WebClient webClient;
    @Autowired
    private UserService userService;

//    @Autowired
//    public TransactionService(WebClient webClient, UserService userService) {
//        this.webClient = webClient;
//        this.userService = userService;
//    }

    /**
     * Update users with expense as 0 after settlement.
     */
    public void updateUserExpensesAfterSettlement() {
        logger.info("entered");
        List<User> allUsers = getAllUsers();

        for (User user : allUsers) {
            logger.info("inside for loop");
            webClient.put()
                    .uri(usersUrl + "/update/{userId}", user.getName())
                    .bodyValue(EXPENSE_AFTER_SETTLEMENT)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        }
        logger.info("outside loop");
    }

    /**
     * Get a list of all users.
     *
     * @return List of all users.
     */
    public List<User> getAllUsers() {
        try {
            return userService.fetchAllUsers(usersUrl);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    /**
     * Fetch transactions that are owed.
     *
     * @return List of borrowers.
     */
    public List<Borrower> fetchTransactionsOwed() {

        List<Borrower> borrowerList = new ArrayList<>();

        List<User> allUsers = getAllUsers();

        for (User user : allUsers) {

            List<Owe> owes = fetchTransactionsOwedBy(user.getName(), allUsers);

            if (!owes.isEmpty()) {

                borrowerList.add(new Borrower(user.getName(), owes));
            }
        }

        return borrowerList;
    }

    /**
     * Fetch transactions owed by a specific user.
     *
     * @param userName  Name of the user.
     * @param allUsers  List of all users.
     * @return List of owes.
     */
    public List<Owe> fetchTransactionsOwedBy(String userName, List<User> allUsers) {

        List<Owe> oweList = new ArrayList<>();

        List<User> otherUsers = userService.fetchOtherUsers(userName, allUsers);

        User mainUser = userService.fetchMainUser(userName, allUsers);

        for (User otherUser : otherUsers) {

            User copiedUser = new User(otherUser);

            if (otherUser.getExpense() > mainUser.getExpense()) {

                copiedUser.setExpense((otherUser.getExpense()
                        - mainUser.getExpense()) / allUsers.size());

                oweList.add(new Owe(copiedUser));
            }
        }

        return oweList;
    }

    /**
     * Fetch transactions to be received.
     *
     * @return List of receivers.
     */
    public List<Receiver> fetchTransactionsToBeReceived() {

        List<Receiver> receiverList = new ArrayList<>();

        List<User> allUsers = getAllUsers();

        for (User user : allUsers) {

            if (user.getExpense() > 0) {

                List<Receive> receives = fetchTransactionsToBeReceivedBy(user.getName(), allUsers);

                if (!receives.isEmpty()) {
                    receiverList.add(new Receiver(user.getName(), receives));
                }
            }
        }

        return receiverList;
    }

    /**
     * Fetch transactions to be received by a specific user.
     *
     * @param userName  Name of the user.
     * @param allUsers  List of all users.
     * @return List of receives.
     */
    public List<Receive> fetchTransactionsToBeReceivedBy(String userName, List<User> allUsers) {

        List<Receive> receiveList = new ArrayList<>();

        List<User> otherUsers = userService.fetchOtherUsers(userName, allUsers);

        User mainUser = userService.fetchMainUser(userName, allUsers);

        for (User otherUser : otherUsers) {

            User copiedUser = new User(otherUser);

            if (mainUser.getExpense() > otherUser.getExpense()) {

                copiedUser.setExpense((mainUser.getExpense()
                        - otherUser.getExpense()) / allUsers.size());

                receiveList.add(new Receive(copiedUser));
            }
        }

        return receiveList;
    }

    /**
     * Calculate settlements and update users.
     *
     * @return List of settlement details.
     */
    public List<String> calculateSettlement() {
            List<String> settlement = new ArrayList<>();

            List<Borrower> borrowers = fetchTransactionsOwed();

            for (Borrower borrower : borrowers) {

                for (Owe owe : borrower.getOwes()) {

                    settlement.add(borrower.getUser()
                            + " pays Rs. "
                            + owe.getAmount()
                            + " to "
                            + owe.getTo()
                            + ".");
                }
            }
            updateUserExpensesAfterSettlement();
            return settlement;
    }
}
