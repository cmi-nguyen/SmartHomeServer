package com.example.demo.controllers;


import com.example.demo.entities.Account;
import com.example.demo.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.util.List;

@RestController
public class AccountController {
    @Autowired private AccountService accountService;

    // Get  mapping
    @CrossOrigin
    @GetMapping("/accounts")
    public List<Account> fetchAccountList(){return accountService.fetchAccountList();}
    // Post Mapping
    @CrossOrigin
    @PostMapping("/accounts")
    public Account saveAccount(@RequestBody Account account) throws NoSuchAlgorithmException {
        return accountService.saveAccount(account);
    }

    // Put Mapping
    @CrossOrigin
    @PutMapping("/accounts/{id}")
    public Account updateAccount(@RequestBody Account account, @PathVariable("id") int accountID){

        return accountService.updateAccount(account,accountID);
    }

    @CrossOrigin
    @GetMapping("/accounts/{id}")
    public Account findById( @PathVariable("id") int accountID){

        return accountService.findByID(accountID);
    }

    // Delete op
    @CrossOrigin
    @DeleteMapping("/accounts/{id}")
    public String deleteAccount(@PathVariable("id") int accountID){
        accountService.deleteAccountByID(accountID);



        return "Delete Successfull";
    }

    //TODOS: login , Encrypt
    @CrossOrigin
    @PostMapping("/login")
    public Account login(@RequestBody Account account){
        return  accountService.login(account);
    }


}
