package com.expensetracker.transaction.controller;

import com.expensetracker.transaction.collection.Borrower;
import com.expensetracker.transaction.collection.Receiver;
import com.expensetracker.transaction.collection.User;
import com.expensetracker.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	private final TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	/**
	 * Retrieve a list of users with pending transactions.
	 *
	 * @return ResponseEntity containing the list of users or an error response.
	 */
	@GetMapping("/pending")
	public ResponseEntity<List<User>> retrievePendingTransactions() {
		try {
			List<User> users = this.transactionService.getAllUsers();
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Retrieve a list of transactions that are owed.
	 *
	 * @return ResponseEntity containing the list of borrowers or an error response.
	 */
	@GetMapping("/owe")
	public ResponseEntity<List<Borrower>> retrieveTransactionsOwed() {
		try {
			List<Borrower> borrowerList = this.transactionService.fetchTransactionsOwed();
			return ResponseEntity.ok(borrowerList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Retrieve a list of transactions to be received.
	 *
	 * @return ResponseEntity containing the list of receivers or an error response.
	 */
	@GetMapping("/receive")
	public ResponseEntity<List<Receiver>> retrieveTransactionsToBeReceived() {
		try {
			List<Receiver> receiverList = this.transactionService.fetchTransactionsToBeReceived();
			return ResponseEntity.ok(receiverList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Calculate and retrieve a list of settlement details.
	 *
	 * @return ResponseEntity containing the list of settlement details or an error response.
	 */
	@GetMapping("/settle")
	public ResponseEntity<List<String>> retrieveSettlement() {
		try {
			List<String> settlement = this.transactionService.calculateSettlement();
			return ResponseEntity.ok(settlement);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
