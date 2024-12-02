package com.example.demo.services;

import com.example.demo.entities.Account;
import com.example.demo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;


    // Save
    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }
    // Read
    @Override
    public List<Account> fetchAccountList() {
        return accountRepository.findAll();
    }

    @Override
    public Account updateAccount(Account account, int accountID) {
        Account accDB = accountRepository.findById(accountID).get();
        if (Objects.nonNull(account.getAccountID())){
            accDB.setAccountID(account.getAccountID());
        }
        if (Objects.nonNull(account.getAccountName())&&!"".equalsIgnoreCase(account.getAccountName())){
            accDB.setAccountName(account.getAccountName());
        }
        if (Objects.nonNull(account.getStatus())){
            accDB.setStatus(account.getStatus());
        }
        if (Objects.nonNull(account.getPassword())&&!"".equalsIgnoreCase(account.getPassword())){
            accDB.setPassword(account.getPassword());
        }
        if (Objects.nonNull(account.getEmail())&&!"".equalsIgnoreCase(account.getEmail())){
            accDB.setEmail(account.getEmail());
        }



        return accountRepository.save(accDB);
    }


    @Override
    public void deleteAccountByID(int accountID) {
        accountRepository.deleteById(accountID);
    }

    @Override
    public Account findByID(int accountID) {
        return accountRepository.findById(accountID).get();
    }

    @Override
    public Account login(Account account) {
       List<Account> accounts = accountRepository.findAll();
       for (int i = 0; i < accounts.size(); i++){
           if (account.getPassword().equals(accounts.get(i).getPassword()) && account.getEmail().equals(accounts.get(i).getEmail())){
               return  accounts.get(i);
           }
       }
       return null;
    }




}
