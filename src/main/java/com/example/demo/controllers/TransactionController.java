package com.example.demo.controllers;


import com.example.demo.entities.Transaction;
import com.example.demo.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    // Read op mapping
    @CrossOrigin
    @GetMapping("/transactions")
    public List<Transaction> fetchAccountList(){return transactionService.fetchTransactionList();}
    // Save op
    @CrossOrigin
    @PostMapping("/transactions")
    public Transaction saveTransaction(@RequestBody Transaction transaction){
        return transactionService.savetransaction(transaction);
    }
    // Update op
    @CrossOrigin
    @PutMapping("/transactions/{id}")
    public Transaction updateTransaction(@RequestBody Transaction account, @PathVariable("id") int transactionId) {
        return transactionService.updateTransaction(account, transactionId);
    }
}
