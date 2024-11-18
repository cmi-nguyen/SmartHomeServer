package com.example.demo.controllers;


import com.example.demo.entities.Account;
import com.example.demo.services.AESEncryptionService;
import com.example.demo.services.AccountService;
import com.example.demo.services.EncDecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
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

        Account temp = account;

        // RSA
        if (account.getRsaKey()==null){
            EncDecService encDecService = new EncDecService();
            try {
                encDecService.init();
                String privateKeyBase64 = Base64.getEncoder().encodeToString(encDecService.privateKey.getEncoded());
                temp.setRsaKey(privateKeyBase64);
            } catch (NoSuchAlgorithmException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        // AES
        if(account.getUserKey()==null){
            try {
                SecretKey key = AESEncryptionService.generateKey(128);
                String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
                temp.setUserKey(base64Key);
                if(account.getPhone()!=null){
                    temp.setPhone(ConvertValue(account.getPhone(),key));
                }
                if(account.getAddress()!=null){
                    temp.setAddress(ConvertValue(account.getAddress(),key));
                }
                if (account.getPin()!=null){
                    temp.setPin(ConvertValue(account.getPin(),key));
                }
                if(account.getEmail()!=null){
                    temp.setEmail(ConvertValue(account.getEmail(),key));
                }

            } catch (NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException |
                     NoSuchPaddingException | InvalidAlgorithmParameterException e) {
                throw new RuntimeException(e);
            }
        }
//        else {
//            String base64AESKeyString = account.getUserKey();
//            // decode the base64 encoded string
//            byte[] decodedKey = Base64.getDecoder().decode(base64AESKeyString);
//            // rebuild key using SecretKeySpec
//            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
//            try {
//                if(account.getPhone()!=null){
//                    temp.setPhone(ConvertValue(account.getPhone(),originalKey));
//                }
//                if(account.getAddress()!=null){
//                    temp.setAddress(ConvertValue(account.getAddress(),originalKey));
//                }
//                if (account.getPin()!=null){
//                    temp.setPin(ConvertValue(account.getPin(),originalKey));
//                }
//                if(account.getEmail()!=null){
//                    temp.setEmail(ConvertValue(account.getEmail(),originalKey));
//                }
//
//
//            } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchPaddingException |
//                     IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
        return accountService.saveAccount(temp);
    }
    public String ConvertValue(String input, SecretKey key) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        IvParameterSpec ivParameterSpec = AESEncryptionService.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";
        return AESEncryptionService.encrypt(algorithm, input, key, ivParameterSpec);
//        String plainText = AESEncryptionService.decrypt(algorithm, cipherText, key, ivParameterSpec);
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
