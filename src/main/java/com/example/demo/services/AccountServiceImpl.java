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
        // Encryption goes here
        // Fields to Encode: phone, address, pin


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
        if (Objects.nonNull(account.getAddress())&&!"".equalsIgnoreCase(account.getAddress())){
            accDB.setAddress(account.getAddress());
        }
        if (Objects.nonNull(account.getPin())&&!"".equalsIgnoreCase(account.getPin())){
            accDB.setPin(account.getPin());
        }
        if (Objects.nonNull(account.getType())){
            accDB.setType(account.getType());
        }



        accDB.setGender(account.isGender());
        if (account.getBalance() != 0 ){
            accDB.setBalance(account.getBalance());
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

    @Override
    public Account generateAndSaveKeysForAccount(Account account) throws NoSuchAlgorithmException {
        // Generate RSA key pair
        KeyPair rsaKeyPair = HybridCryptosystem.generateRSAKeyPair(); // Use previously defined method

        // Convert keys to Base64 for storage
        String publicKey = KeyUtils.encodeKeyToBase64(rsaKeyPair.getPublic());
        String privateKey = KeyUtils.encodeKeyToBase64(rsaKeyPair.getPrivate()); // Encrypt in production

        // Optional: Generate AES key for symmetric encryption
        SecretKey aesKey = HybridCryptosystem.generateAESKey();
        String aesKeyEncoded = KeyUtils.encodeKeyToBase64(aesKey); // Encrypt in production

        // Save keys to the account entity
        account.setRsaPublicKey(publicKey);
        account.setRsaPrivateKey(privateKey);

        // Save account to database
        accountRepository.save(account);
        return account;
    }
}
