package com.expensetracker.transaction.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String name;
	private Integer expense;

	public User(User other) {
		this.name = other.getName();
		this.expense = other.getExpense();
	}
}
