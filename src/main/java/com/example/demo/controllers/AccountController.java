package com.example.demo.controllers;


import com.example.demo.entities.Account;
import com.example.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired private AccountService accountService;

    // Read op mapping
    @CrossOrigin
    @GetMapping("/accounts")
    public List<Account> fetchAccountList(){return accountService.fetchAccountList();}
    // Save op
    @CrossOrigin
    @PostMapping("/accounts")
    public Account saveAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }
    // Update op
    @CrossOrigin
    @PutMapping("/accounts/{id}")
    public Account updateAccount(@RequestBody Account account, @PathVariable("id") int accountID){
        return accountService.updateAccount(account,accountID);
    }

    // Delete op
    @CrossOrigin
    @DeleteMapping("/accounts/{id}")
    public String deleteAccount(@PathVariable("id") int accountID){
        accountService.deleteAccountByID(accountID);
        return "Delete Successfull";
    }

    //TODOS: login , Encrypt




}
