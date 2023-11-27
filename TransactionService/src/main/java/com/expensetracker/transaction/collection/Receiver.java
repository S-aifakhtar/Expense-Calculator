package com.expensetracker.transaction.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receiver {
    private String user;
    // Initializing with an empty list so that it's never null
    private List<Receive> toReceive = Collections.emptyList();
}
