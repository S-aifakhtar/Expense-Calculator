package com.expensetracker.transaction.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receive {
    private String from;
    private Integer amount;

    public Receive(User user) {
        this.from = user.getName();
        this.amount = user.getExpense();
    }
}
