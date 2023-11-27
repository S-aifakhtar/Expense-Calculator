package com.expensetracker.transaction.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Owe {
    private String to;
    private Integer amount;

    public Owe(User user) {
        this.to = user.getName();
        this.amount = user.getExpense();
    }
}
