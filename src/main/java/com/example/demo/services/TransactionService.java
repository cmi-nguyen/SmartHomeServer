package com.example.demo.services;


import com.example.demo.entities.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction savetransaction(Transaction account);

    // Read operation
    List<Transaction> fetchTransactionList();

    // Update operation
    Transaction updateTransaction(Transaction transaction, int transactionId);

    // Find by ID
    Transaction findById(int transactionId);
    // Delete operation

//    void deleteAccountByID(int accountID);
}
