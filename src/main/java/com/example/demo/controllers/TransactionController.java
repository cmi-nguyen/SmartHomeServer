package com.example.demo.controllers;


import com.example.demo.entities.Account;
import com.example.demo.entities.Transaction;
import com.example.demo.services.AccountService;
import com.example.demo.services.HybridCryptosystem;
import com.example.demo.services.KeyUtils;
import com.example.demo.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;

    // Read op mapping
    @CrossOrigin
    @GetMapping("/transactions")
    public List<Transaction> fetchAccountList(){return transactionService.fetchTransactionList();}
    // Save op
    @CrossOrigin
    @PostMapping("/transactions")
    public Transaction saveTransaction(@RequestBody Transaction transaction, @RequestHeader(name = "rsaKeyBase64") String rsaKeyBase64 ) throws Exception {
        Account receiverAccount = accountService.findByID(transaction.getReceiverid());
        String AESKeyString = "";
        SecretKey AESKey = HybridCryptosystem.generateAESKey();
        PublicKey RSAPublicKey = KeyUtils.decodePublicKeyFromBase64(receiverAccount.getRsaPublicKey());
        byte[] encryptedAESKey = HybridCryptosystem.encryptRSA(AESKey.getEncoded(), RSAPublicKey);
        AESKeyString =  Base64.getEncoder().encodeToString(encryptedAESKey);
        transaction.setAESKey(AESKeyString);
        String data = transaction.getMessage();
        byte[] encryptedData = HybridCryptosystem.encryptAES(data.getBytes(), AESKey);
        // Set data
        transaction.setMessage(Base64.getEncoder().encodeToString(encryptedData));
        return transactionService.savetransaction(transaction);
    }
    // Update op
    @CrossOrigin
    @PutMapping("/transactions/{id}")
    public Transaction updateTransaction(@RequestBody Transaction transaction, @PathVariable("id") int transactionId) {
        return transactionService.updateTransaction(transaction, transactionId);
    }
}
