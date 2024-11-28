package com.example.demo.controllers;


import com.example.demo.entities.Account;
import com.example.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
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
    public Transaction saveTransaction(@RequestBody Transaction transaction) throws Exception {
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

    @CrossOrigin
    @PostMapping("/transactions/{id}/decrypt")
    public String decryptMessage(@RequestBody String encryptedMessage, @PathVariable("id") int transactionId, @RequestHeader(name = "rsaKeyBase64") String rsaKeyBase64 ) throws Exception {
        // Get Transaction from ID
        Transaction transaction = transactionService.findById(transactionId);
        Account account = accountService.findByID(transaction.getReceiverid());

//        String EncryptedMessage = "oBv+Om9g/coyIJA6Nm7K4w==";
       // String EncryptedMessage = transaction.getMessage();
        String EncryptedMessage = encryptedMessage;

//        String AESEncryptedKey = "GfjHnHKp44r2r/yWLzjaHtLhdn/Od24wBWoVxX0mkxgvEcdVaj6AVbD0AEHZHXhrwTJnhdHLtKOAnF9JSYACn5tlnLjn5MtgUrlk90idJFSvcgg6emff1rN6FTh6hJpiLS8Fa7kT/qIhlMilBMxorN/dUEG/2sYgqMh395MSBsF3VU1PT8TyJzJwiK5ga7Q3abnw8fVqdCR2HdaJSPoEQo7nilRz2Vmhqc2ZPU+g3R0bFhbr34GL84SwtgM5uBviP8rcZwM8aZhpQB2VLIODGNzTZhLtg12ZpLtAPFt0a0hNFttkN0J5HVKm3rv83i/xXhW97RLPMeLBsE4nenygNg==";
        String  AESEncryptedKey = transaction.getAESKey();

        //        String RSAKeyString = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDMUqiK/PawYmnusrUk3aikbn0+6iMih41foQOrcXjjh9KDPYvX6sDjMyqUo0nCW//s+bTjMdArR/yOQxrtlLKBhGSD9OarWGKmD5fqLWSe1EMAmO3imNzr4f8JdwgDIQPYblmes2iIzZraRti2X/TPKg85X5eDIqCBT+oEGev4DATCy92ZL0ccNxwuhrMVjJpHdjGQVIt6YWXeo6V0c/n9eNRmw/Ekax7945ETDkkHpWNFF04AsQZlrEt3TflEigbwvu/y4ZzEkwl6axJEi/C5RlTYEMhMrdm3nUYVS9z8HmyQi26Ct5zAmJmz95eUDVxz4bIJAKlMhADelJ/XpzORAgMBAAECggEAHgOscj0GaKhkTaYJ2cBdTsj+SVjapW61KU2Sp5jT0dS4wK0GAcdLOMo3iz3QuXuLRP6fgFpnwoaLcUMmowIwwLmNNxx+1Wp2lKn+E8PsSoVS0bUlXh/ndW0tlPCGiEh4HfnZMhNtG9+Gy7a+4feG6PcRj8jc4xdvQt+q4u8DN1zFXTJkboK3Dw6jLrtIIs9jBUsU8ozupig69UdzobYgrX8I/geFgocG3nhLfldK8tgcwx9+qGLA99gTtj6yaVQCwLXooZ/NgJ724Kn7WxcXvSbsMCCKHb1u+uYDPwuBDIFWgEpfAnSiuxx7tF3PiiOaWDgGJWQEx0pHQRFz6E27UwKBgQDt9uIr67Cz8S9kAhEYr7zfvbHUgbTEvBaO4mpQ1nZu0pKLsk8Vij8APdxKtSZ/WkbmpR7zZt8M2M4kDHwrYixYd7RCFPN695FXoaLI3ydQGEGi+faotzVyO9C8OzzpbxEZrL1qbq/kHfL5pN3FRE7UhWXcbKAi8jGhtC5XOHSlrwKBgQDbzws48AJLKiJV4uv5UVgkh/uR2vSta+bshQV+MWN69zal42zn6+9/thwh43462wL6flfHUrMLRDN7/ddrc3WrVWVkIMdBpoZ5fMDSpnInW1Opiel4605cndl3iIAT32LyruHikZz16cKQ5sUoLlLswNRrH5RDRa5OUvRu5LVKvwKBgEcvhKco4Xpi2w9qwtyErdass4bjqkT0Ezd5Kd622UT7vxUFKT1DVudmmgDLQhH02qLioGilzHHWZYxJI9gCjUuFu6nNBK0zZAo38y9t+h+usevkJNKBw/5BQIcenXEjZZUtpJgzNnDiOK5PkHwTnBVdNoSDBkCN/STndrlmzRZHAoGBAJL78FUyqVX55tvn0kP10Sscg20VLWiKLvEaQoIQJKWlijWDzt+HNbAA5u8CYPaaXXOD6M6wzmlKQeM360U/AACCKjq6cIPGB0ZV1dsG0m7e8yL7xdPGBbCJHVKBRKQFTPVbXFFVbdBDcIfUySIFWA5ZI7I+e7ZAcj6N2kYNSe2hAoGAR0LjvgA0+pyIJeIl3rBI6i/vxHQ744Xml7VSY1siFBmEGbdh5bAn2mu936mPBJm33r1/z55mP3w9p1FPc9uINyXpG0VYyu9xoQgEsevc5In6XQNKg1rLXWFyluGLHbkCaIria+Lw77XxQe1OvUngXOV2325s9mALRewfvmEQfd4=";
        // Step 1: Decode the RSA private key
        PrivateKey privateKey = decodePrivateKeyFromBase64(rsaKeyBase64);

        // Step 2: Decrypt the AES key using the RSA private key
        byte[] encryptedAESKeyBytes = Base64.getDecoder().decode(AESEncryptedKey);
        Cipher rsaCipher = Cipher.getInstance("RSA");
        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] aesKeyBytes = rsaCipher.doFinal(encryptedAESKeyBytes);
        SecretKey aesKey = new SecretKeySpec(aesKeyBytes, "AES");

        // Step 3: Decrypt the message using the AES key
        byte[] encryptedMessageBytes = Base64.getDecoder().decode(EncryptedMessage);
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] decryptedMessageBytes = aesCipher.doFinal(encryptedMessageBytes);

        // Print the decrypted message
        String decryptedMessage = new String(decryptedMessageBytes);
        System.out.println("Decrypted Message: " + decryptedMessage);
        return decryptedMessage;
    }
    public static PrivateKey decodePrivateKeyFromBase64(String base64PrivateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(base64PrivateKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
    }
    // Update op
    @CrossOrigin
    @PutMapping("/transactions/{id}")
    public Transaction updateTransaction(@RequestBody Transaction transaction, @PathVariable("id") int transactionId) {
        return transactionService.updateTransaction(transaction, transactionId);
    }
}
