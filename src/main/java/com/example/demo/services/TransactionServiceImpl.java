package com.example.demo.services;


import com.example.demo.entities.Transaction;

import com.example.demo.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction savetransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> fetchTransactionList() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction updateTransaction(Transaction transaction, int transactionId) {
        Transaction trasnDB = transactionRepository.findById(transactionId).get();
        if (Objects.nonNull(transaction.getTransactionID())){
            trasnDB.setTransactionID(transaction.getTransactionID());
        }
        if (Objects.nonNull(transaction.getMessage())&&!"".equalsIgnoreCase(transaction.getMessage())){
            trasnDB.setMessage(transaction.getMessage());
        }
        trasnDB.setAmount(transaction.getAmount());
        if (Objects.nonNull(transaction.getReceiverName())&&!"".equalsIgnoreCase(transaction.getReceiverName())){
            trasnDB.setReceiverName(transaction.getReceiverName());
        }
        if (Objects.nonNull(transaction.getReceiverPhone())&&!"".equalsIgnoreCase(transaction.getReceiverPhone())){
            trasnDB.setReceiverPhone(transaction.getReceiverPhone());
        }
        if (Objects.nonNull(transaction.getSenderName())&&!"".equalsIgnoreCase(transaction.getSenderName())){
            trasnDB.setSenderPhone(transaction.getSenderPhone());
        }
        if (Objects.nonNull(transaction.getTransactionDate())){
            trasnDB.setTransactionDate(transaction.getTransactionDate());
        }
        if (0>(transaction.getTransactionFee())){
            trasnDB.setTransactionFee(transaction.getTransactionFee());
        }
        if(transaction.getSenderId()!=0){
            trasnDB.setSenderId(transaction.getSenderId());
        }
        if(transaction.getReceiverid()!=0){
            trasnDB.setReceiverid(transaction.getReceiverid());
        }


        return transactionRepository.save(trasnDB);
    }
}
